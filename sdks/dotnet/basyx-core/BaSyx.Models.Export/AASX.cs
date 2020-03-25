/*******************************************************************************
* Copyright (c) 2020 Robert Bosch GmbH
* Author: Constantin Ziesche (constantin.ziesche@bosch.com)
*
* This program and the accompanying materials are made available under the
* terms of the Eclipse Public License 2.0 which is available at
* http://www.eclipse.org/legal/epl-2.0
*
* SPDX-License-Identifier: EPL-2.0
*******************************************************************************/
using System;
using System.Collections.Generic;
using System.IO;
using System.IO.Packaging;
using System.Linq;
using BaSyx.Models.Core.AssetAdministrationShell.Identification;
using BaSyx.Models.Core.AssetAdministrationShell.Generics.SubmodelElementTypes;
using NLog;
using Microsoft.AspNetCore.StaticFiles;
using BaSyx.Utils.FileHandling;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;

namespace BaSyx.Models.Export
{
    public sealed class AASX : IDisposable
    {
        public const string ROOT_FOLDER = "/";
        public const string AASX_FOLDER = "/aasx";

        public const string ORIGIN_RELATIONSHIP_TYPE = "http://www.admin-shell.io/aasx/relationships/aasx-origin";
        public const string SPEC_RELATIONSHIP_TYPE = "http://www.admin-shell.io/aasx/relationships/aas-spec";
        public const string SUPPLEMENTAL_RELATIONSHIP_TYPE = "http://www.admin-shell.io/aasx/relationships/aas-suppl";
        public const string THUMBNAIL_RELATIONSHIP_TYPE = "http://schemas.openxmlformats.org/package/2006/relationships/metadata/thumbnail";

        public static readonly Uri ORIGIN_URI = new Uri("/aasx/aasx-origin", UriKind.RelativeOrAbsolute);
        private static readonly Logger logger = LogManager.GetCurrentClassLogger();

        public List<PackagePart> SupplementaryFiles { get; } = new List<PackagePart>();

        private Package aasxPackage;
        private PackagePart originPart;
        private PackagePart specPart;

        public AASX(Package package)
        {
            aasxPackage = package;

            LoadOrCreateOrigin();
            LoadSpec();
        }

        public AASX(string aasxFilePath)
        {
            aasxPackage = Package.Open(aasxFilePath, FileMode.OpenOrCreate, FileAccess.ReadWrite, FileShare.Read);

            LoadOrCreateOrigin();
            LoadSpec();
            LoadSupplementaryFiles();
        }

        private void LoadSupplementaryFiles()
        {
            if (specPart != null)
            {
                PackageRelationshipCollection relationships = specPart.GetRelationshipsByType("http://www.admin-shell.io/aasx/relationships/aas-suppl");
                foreach (var relationship in relationships)
                {
                    try
                    {
                        PackagePart file = aasxPackage.GetPart(relationship.TargetUri);
                        SupplementaryFiles.Add(file);
                    }
                    catch(Exception e)
                    {
                        logger.Warn(e, "Relationsship " + relationship.TargetUri + "does not exist in the package - Exception: " + e.Message);
                        continue;
                    }
                  
                }
            }
        }

        private void LoadOrCreateOrigin()
        {
            PackageRelationshipCollection relationships = aasxPackage.GetRelationshipsByType(ORIGIN_RELATIONSHIP_TYPE);
            originPart = relationships?.Where(r => r.TargetUri == ORIGIN_URI)?.Select(p => aasxPackage.GetPart(p.TargetUri))?.FirstOrDefault();
            if(originPart == null)
            {
                originPart = aasxPackage.CreatePart(ORIGIN_URI, System.Net.Mime.MediaTypeNames.Text.Plain, CompressionOption.Maximum);
                originPart.GetStream(FileMode.Create).Dispose();
                aasxPackage.CreateRelationship(originPart.Uri, TargetMode.Internal, ORIGIN_RELATIONSHIP_TYPE);
            }
        }

        private void LoadSpec()
        {
            if(originPart != null)
            {
                PackageRelationshipCollection relationships = originPart.GetRelationshipsByType(SPEC_RELATIONSHIP_TYPE);
                specPart = relationships?.Select(s => aasxPackage.GetPart(s.TargetUri))?.FirstOrDefault();
            }
        }

        private void ClearRelationshipAndPartFromPackagePart(PackagePart sourcePackagePart, string relationshipType, Uri targetUri)
        {
            PackageRelationshipCollection relationships = sourcePackagePart.GetRelationshipsByType(relationshipType);
            foreach (var relationship in relationships.ToList())
                if (relationship.TargetUri == targetUri)
                        sourcePackagePart.DeleteRelationship(relationship.Id);

            if (aasxPackage.PartExists(targetUri))
                aasxPackage.DeletePart(targetUri);
        }
        private void ClearRelationshipAndPartFromPackage(string relationshipType, Uri targetUri)
        {
            PackageRelationshipCollection relationships = aasxPackage.GetRelationshipsByType(relationshipType);
            foreach (var relationship in relationships.ToList())
                if (relationship.TargetUri == targetUri)
                    aasxPackage.DeleteRelationship(relationship.Id);

            if (aasxPackage.PartExists(targetUri))
                aasxPackage.DeletePart(targetUri);
        }


        private void ClearRelationshipsAndPartFromPackage(string relationshipType)
        {
            PackageRelationshipCollection relationships = aasxPackage.GetRelationshipsByType(relationshipType);
            foreach (var relationship in relationships.ToList())
            {
                aasxPackage.DeleteRelationship(relationship.Id);

                if(aasxPackage.PartExists(relationship.TargetUri))
                    aasxPackage.DeletePart(relationship.TargetUri);
            }           
        }

        private void ClearRelationshipsAndPartFromPackagePart(PackagePart sourcePackagePart, string relationshipType)
        {
            PackageRelationshipCollection relationships = sourcePackagePart.GetRelationshipsByType(relationshipType);
            foreach (var relationship in relationships.ToList())
            {
                sourcePackagePart.DeleteRelationship(relationship.Id);

                if (aasxPackage.PartExists(relationship.TargetUri))
                    aasxPackage.DeletePart(relationship.TargetUri);
            }
        }

        public void AddThumbnail(string thumbnailPath)
        {
            ClearRelationshipsAndPartFromPackage(THUMBNAIL_RELATIONSHIP_TYPE);

            string thumbnailUriPath = ROOT_FOLDER + Path.GetFileName(thumbnailPath);

            Uri partUri = PackUriHelper.CreatePartUri(new Uri(thumbnailUriPath, UriKind.RelativeOrAbsolute));
            PackagePart thumbnailPart = aasxPackage.CreatePart(partUri, GetContentType(thumbnailPath), CompressionOption.Maximum);
            aasxPackage.CreateRelationship(thumbnailPart.Uri, TargetMode.Internal, THUMBNAIL_RELATIONSHIP_TYPE);
            CopyFileToPackagePart(thumbnailPart, thumbnailPath);
        }

        public void AddEnvironment(Identifier aasId, string aasEnvPath)
        {
            if (aasId == null)
                throw new ArgumentNullException("aasId");
            if (string.IsNullOrEmpty(aasEnvPath))
                throw new ArgumentNullException("aasEnvPath");
            if(!System.IO.File.Exists(aasEnvPath))
                throw new InvalidOperationException(aasEnvPath + " does not exist");
        
            string aasIdName = aasId.Id;
            foreach (char invalidChar in Path.GetInvalidFileNameChars())
                aasIdName = aasIdName.Replace(invalidChar, '_');

            string aasFilePath = "/aasx/" + aasIdName + "/" + aasIdName + ".aas" + Path.GetExtension(aasEnvPath);

            Uri partUri = PackUriHelper.CreatePartUri(new Uri(aasFilePath, UriKind.RelativeOrAbsolute));
            ClearRelationshipAndPartFromPackagePart(originPart, SPEC_RELATIONSHIP_TYPE, partUri);

            specPart = aasxPackage.CreatePart(partUri, GetContentType(aasEnvPath), CompressionOption.Maximum);
            originPart.CreateRelationship(specPart.Uri, TargetMode.Internal, SPEC_RELATIONSHIP_TYPE);

            CopyFileToPackagePart(specPart, aasEnvPath);
        }

        public void AddEnvironment(Identifier aasId, AssetAdministrationShellEnvironment_V2_0 environment, ExportType exportType)
        {
            if (aasId == null)
                throw new ArgumentNullException(nameof(aasId));
            if (environment == null)
                throw new ArgumentNullException(nameof(environment));

            string aasIdName = aasId.Id;
            foreach (char invalidChar in Path.GetInvalidFileNameChars())
                aasIdName = aasIdName.Replace(invalidChar, '_');

            string aasFilePath = "/aasx/" + aasIdName + "/" + aasIdName + ".aas." + exportType.ToString().ToLower();

            Uri partUri = PackUriHelper.CreatePartUri(new Uri(aasFilePath, UriKind.RelativeOrAbsolute));
            ClearRelationshipAndPartFromPackagePart(originPart, SPEC_RELATIONSHIP_TYPE, partUri);

            specPart = aasxPackage.CreatePart(partUri, GetContentType(aasFilePath), CompressionOption.Maximum);
            originPart.CreateRelationship(specPart.Uri, TargetMode.Internal, SPEC_RELATIONSHIP_TYPE);

            string environmentTemp = Path.GetRandomFileName() + "." + exportType.ToString().ToLower();
            environment.WriteEnvironment_V2_0(exportType, environmentTemp);

            CopyFileToPackagePart(specPart, environmentTemp);

            File.Delete(environmentTemp);
        }

        public AssetAdministrationShellEnvironment_V1_0 GetEnvironment_V1_0()
        {
            if(specPart?.Uri != null)
            {
                string specFilePath = specPart.Uri.ToString();
                string extension = Path.GetExtension(specFilePath)?.ToLower();
                AssetAdministrationShellEnvironment_V1_0 environment;
                switch (extension)
                {
                    case ".json":
                        {
                            using (Stream file = specPart.GetStream(FileMode.Open, FileAccess.Read))
                                environment = AssetAdministrationShellEnvironment_V1_0.ReadEnvironment_V1_0(file, ExportType.Json);
                        }
                        break;
                    case ".xml":
                        {
                            using (Stream file = specPart.GetStream(FileMode.Open, FileAccess.Read))
                                environment = AssetAdministrationShellEnvironment_V1_0.ReadEnvironment_V1_0(file, ExportType.Xml);
                        }
                        break;
                    default:
                        logger.Error("Not supported file format: " + extension);
                        environment = null;
                        break;
                }
                return environment;
            }
            return null;
        }

        public AssetAdministrationShellEnvironment_V2_0 GetEnvironment_V2_0()
        {
            if (specPart?.Uri != null)
            {
                string specFilePath = specPart.Uri.ToString();
                string extension = Path.GetExtension(specFilePath)?.ToLower();
                AssetAdministrationShellEnvironment_V2_0 environment;
                switch (extension)
                {
                    case ".json":
                        {
                            using (Stream file = specPart.GetStream(FileMode.Open, FileAccess.Read))
                                environment = AssetAdministrationShellEnvironment_V2_0.ReadEnvironment_V2_0(file, ExportType.Json);
                        }
                        break;
                    case ".xml":
                        {
                            using (Stream file = specPart.GetStream(FileMode.Open, FileAccess.Read))
                                environment = AssetAdministrationShellEnvironment_V2_0.ReadEnvironment_V2_0(file, ExportType.Xml);
                        }
                        break;
                    default:
                        logger.Error("Not supported file format: " + extension);
                        environment = null;
                        break;
                }
                return environment;
            }
            return null;
        }

        public void AddFilesToAASX(Dictionary<string, IFile> fileDestinationMapping, CompressionOption compressionOption = CompressionOption.Maximum)
        {
            for (int i = 0; i < fileDestinationMapping.Count; i++)
            {
                string filePath = fileDestinationMapping.ElementAt(i).Key;
                IFile file = fileDestinationMapping.ElementAt(i).Value;

                string relativeDestination;
                if (!file.Value.StartsWith(AASX_FOLDER))
                    relativeDestination = AASX_FOLDER + file.Value;
                else
                    relativeDestination = file.Value;

                Uri uri = PackUriHelper.CreatePartUri(new Uri(relativeDestination, UriKind.Relative));

                ClearRelationshipAndPartFromPackagePart(specPart, SUPPLEMENTAL_RELATIONSHIP_TYPE, uri);

                string contentType;
                if (!string.IsNullOrEmpty(file.MimeType))
                    contentType = file.MimeType;
                else
                    contentType = GetContentType(filePath);

                PackagePart packagePart = aasxPackage.CreatePart(uri, contentType, compressionOption);
                specPart.CreateRelationship(packagePart.Uri, TargetMode.Internal, SUPPLEMENTAL_RELATIONSHIP_TYPE);

                CopyFileToPackagePart(packagePart, filePath);
            }
        }

        public void AddFileToAASX(string targetUri, string filePath, CompressionOption compressionOption = CompressionOption.Maximum)
        {
            string relativeDestination;
            if (!targetUri.StartsWith(AASX_FOLDER))
                relativeDestination = AASX_FOLDER + targetUri;
            else
                relativeDestination = targetUri;

            Uri uri = PackUriHelper.CreatePartUri(new Uri(relativeDestination, UriKind.Relative));

            ClearRelationshipAndPartFromPackagePart(specPart, SUPPLEMENTAL_RELATIONSHIP_TYPE, uri);

            string contentType = GetContentType(filePath);

            PackagePart packagePart = aasxPackage.CreatePart(uri, contentType, compressionOption);
            specPart.CreateRelationship(packagePart.Uri, TargetMode.Internal, SUPPLEMENTAL_RELATIONSHIP_TYPE);

            CopyFileToPackagePart(packagePart, filePath);
        }

        private void CopyStreamToPackagePart(PackagePart packagePart, Stream stream)
        {
            using (stream)
            {
                using (Stream destination = packagePart.GetStream())
                {
                    stream.CopyTo(destination);
                }
            }
        }

        private void CopyFileToPackagePart(PackagePart packagePart, string filePath)
        {
            using (FileStream fileStream = new FileStream(filePath, FileMode.Open, FileAccess.Read))
            {
                using (Stream destination = packagePart.GetStream())
                {
                    fileStream.CopyTo(destination);
                }
            }
        }

        private static string GetContentType(string filePath)
        {
            if (!new FileExtensionContentTypeProvider().TryGetContentType(filePath, out string contentType))
                if (!MimeTypes.TryGetContentType(filePath, out contentType))
                    return null;

            return contentType;
        }

        public void Dispose()
        {
            aasxPackage.Close();
        }
    }
}