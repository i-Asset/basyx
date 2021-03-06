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
using BaSyx.Models.Core.AssetAdministrationShell.Generics;
using BaSyx.Models.Core.AssetAdministrationShell.Generics.SubmodelElementTypes;
using BaSyx.Models.Core.AssetAdministrationShell.Implementations;
using BaSyx.Models.Core.AssetAdministrationShell.References;
using BaSyx.Models.Core.AssetAdministrationShell.Identification;
using BaSyx.Models.Export.Converter;
using System;
using System.Collections.Generic;
using System.Linq;
using BaSyx.Models.Core.AssetAdministrationShell.Semantics;
using BaSyx.Models.Core.Common;
using BaSyx.Models.Core.AssetAdministrationShell.Implementations.SubmodelElementTypes;
using Range = BaSyx.Models.Core.AssetAdministrationShell.Implementations.SubmodelElementTypes.Range;

namespace BaSyx.Models.Export.EnvironmentSubmodelElements
{
    public static class EnvironmentSubmodelElementFactory_V2_0
    {
        public static ISubmodelElement ToSubmodelElement(this SubmodelElementType_V2_0 envSubmodelElement, List<IConceptDescription> conceptDescriptions)
        {
            if (envSubmodelElement == null)
                return null;

            ModelType modelType = envSubmodelElement.ModelType;

            if (modelType == null)
                return null;

            SubmodelElement submodelElement = null;

            if (modelType == ModelType.Property && envSubmodelElement is Property_V2_0 castedProperty)
            {
                DataObjectType dataObjectType;
                if (string.IsNullOrEmpty(castedProperty.ValueType))
                    dataObjectType = DataObjectType.None;
                else if (!DataObjectType.TryParse(castedProperty.ValueType, out dataObjectType))
                    return null;

                Property property = new Property(new DataType(dataObjectType))
                {
                    Value = castedProperty.Value,
                    ValueId = castedProperty.ValueId?.ToReference_V2_0()
                };

                submodelElement = property;
            }
            else if (modelType == ModelType.MultiLanguageProperty && envSubmodelElement is MultiLanguageProperty_V2_0 castedMultiLanguageProperty)
            {
                MultiLanguageProperty multiLanguageProperty = new MultiLanguageProperty
                {
                    Value = castedMultiLanguageProperty.Value,
                    ValueId = castedMultiLanguageProperty.ValueId?.ToReference_V2_0()
                };

                submodelElement = multiLanguageProperty;
            }
            else if (modelType == ModelType.Range && envSubmodelElement is Range_V2_0 castedRange)
            {
                if (!DataObjectType.TryParse(castedRange.ValueType, out DataObjectType dataObjectType))
                    return null;

                Range range = new Range()
                {
                    Min = new ElementValue(castedRange.Min, new DataType(dataObjectType)),
                    Max = new ElementValue(castedRange.Max, new DataType(dataObjectType)),
                    ValueType = new DataType(dataObjectType)
                };

                submodelElement = range;
            }            
            else if (modelType == ModelType.File && envSubmodelElement is File_V2_0 castedFile)
            {
                File file = new File
                {
                    MimeType = castedFile.MimeType,
                    Value = castedFile.Value
                };

                submodelElement = file;
            }
            else if (modelType == ModelType.Blob && envSubmodelElement is Blob_V2_0 castedBlob)
            {
                Blob blob = new Blob
                {
                    MimeType = castedBlob.MimeType,
                    Value = Convert.FromBase64String(castedBlob.Value)
                };

                submodelElement = blob;
            }
            else if (modelType == ModelType.RelationshipElement && envSubmodelElement is RelationshipElement_V2_0 castedRelationshipElement)
            {
                RelationshipElement relationshipElement = new RelationshipElement
                {
                    First = castedRelationshipElement.First?.ToReference_V2_0<IReferable>(),
                    Second = castedRelationshipElement.Second?.ToReference_V2_0<IReferable>()
                };

                submodelElement = relationshipElement;
            }
            else if (modelType == ModelType.AnnotatedRelationshipElement && envSubmodelElement is AnnotatedRelationshipElement_V2_0 castedAnnotatedRelationshipElement)
            {
                AnnotatedRelationshipElement annotatedRelationshipElement = new AnnotatedRelationshipElement()
                {
                    First = castedAnnotatedRelationshipElement.First?.ToReference_V2_0<IReferable>(),
                    Second = castedAnnotatedRelationshipElement.Second?.ToReference_V2_0<IReferable>(),
                    Annotation = castedAnnotatedRelationshipElement.Annotation?.ToReference_V2_0<ISubmodelElement>()
                };

                submodelElement = annotatedRelationshipElement;
            }
            else if (modelType == ModelType.ReferenceElement && envSubmodelElement is ReferenceElement_V2_0 castedReferenceElement)
            {
                ReferenceElement referenceElement = new ReferenceElement
                {
                    Value = castedReferenceElement.Value?.ToReference_V2_0()
                };

                submodelElement = referenceElement;
            }
            else if (modelType == ModelType.Event && envSubmodelElement is Event_V2_0 castedEvent)
            {
                Event eventable = new Event();

                submodelElement = eventable;
            }
            else if (modelType == ModelType.BasicEvent && envSubmodelElement is BasicEvent_V2_0 castedBasicEvent)
            {
                BasicEvent basicEvent = new BasicEvent()
                {
                    Observed = castedBasicEvent.Observed.ToReference_V2_0<IReferable>()
                };

                submodelElement = basicEvent;
            }
            else if (modelType == ModelType.Entity && envSubmodelElement is Entity_V2_0 castedEntity)
            {
                Entity entity = new Entity()
                {
                    EntityType = (EntityType)Enum.Parse(typeof(EntityType), castedEntity.EntityType.ToString()),
                    Asset = castedEntity.AssetReference.ToReference_V2_0<IAsset>()
                };

                var statements = castedEntity.Statements?.ConvertAll(c => c.submodelElement.ToSubmodelElement(conceptDescriptions));
                if (statements?.Count > 0)
                    foreach (var element in statements)
                        entity.Statements.Add(element);

                submodelElement = entity;
            }
            else if (modelType == ModelType.Operation && envSubmodelElement is Operation_V2_0 castedOperation)
            {
                Operation operation = new Operation
                {
                    InputVariables = new OperationVariableSet(),
                    OutputVariables = new OperationVariableSet(),
                    InOutputVariables = new OperationVariableSet()
                };

                var operationInElements = castedOperation.InputVariables?.ConvertAll(c => c.Value?.submodelElement?.ToSubmodelElement(conceptDescriptions));
                if(operationInElements?.Count > 0)
                    foreach (var element in operationInElements)
                        operation.InputVariables.Add(element);
                
                var operationOutElements = castedOperation.OutputVariables?.ConvertAll(c => c.Value?.submodelElement?.ToSubmodelElement(conceptDescriptions));
                if (operationOutElements?.Count > 0)
                    foreach (var element in operationOutElements)
                        operation.OutputVariables.Add(element);

                var operationInOutElements = castedOperation.InOutputVariables?.ConvertAll(c => c.Value?.submodelElement?.ToSubmodelElement(conceptDescriptions));
                if (operationInOutElements?.Count > 0)
                    foreach (var element in operationInOutElements)
                        operation.InOutputVariables.Add(element);

                submodelElement = operation;
            }
            else if (modelType == ModelType.SubmodelElementCollection && envSubmodelElement is SubmodelElementCollection_V2_0 castedSubmodelElementCollection)
            {
                SubmodelElementCollection submodelElementCollection = new SubmodelElementCollection();

                if (castedSubmodelElementCollection.Value?.Count > 0)
                {
                    submodelElementCollection.Value = new ElementContainer<ISubmodelElement>();
                    List<ISubmodelElement> smElements = castedSubmodelElementCollection.Value?.ConvertAll(c => c.submodelElement?.ToSubmodelElement(conceptDescriptions));
                    foreach (var smElement in smElements)
                        submodelElementCollection.Value.Add(smElement);
                }

                submodelElement = submodelElementCollection;
            }


            if (submodelElement == null)
                return null;

            submodelElement.Category = envSubmodelElement.Category;
            submodelElement.Description = envSubmodelElement.Description;
            submodelElement.IdShort = envSubmodelElement.IdShort;
            submodelElement.Kind = envSubmodelElement.Kind;
            submodelElement.SemanticId = envSubmodelElement.SemanticId?.ToReference_V2_0();
            submodelElement.Parent = string.IsNullOrEmpty(envSubmodelElement.Parent) ? null :
                    new Reference(
                        new Key(KeyElements.AssetAdministrationShell, KeyType.IRI, envSubmodelElement.Parent, true));
            submodelElement.Constraints = null;

            string semanticId = envSubmodelElement.SemanticId?.Keys?.FirstOrDefault()?.Value;
            if (!string.IsNullOrEmpty(semanticId))
            {
                submodelElement.ConceptDescription =
                    conceptDescriptions.Find(f => f.Identification.Id == semanticId);
                submodelElement.EmbeddedDataSpecifications = submodelElement.ConceptDescription?.EmbeddedDataSpecifications;
            }

            return submodelElement;
        }

        public static EnvironmentSubmodelElement_V2_0 ToEnvironmentSubmodelElement_V2_0(this ISubmodelElement element)
        {
            if (element == null)
                return null;
            ModelType modelType = element.ModelType;

            if (modelType == null)
                return null;

            EnvironmentSubmodelElement_V2_0 environmentSubmodelElement = new EnvironmentSubmodelElement_V2_0();

            SubmodelElementType_V2_0 submodelElementType = new SubmodelElementType_V2_0()
            {
                Category = element.Category,
                Description = element.Description,
                IdShort = element.IdShort,
                Kind = element.Kind,
                Parent = element.Parent?.First?.Value,
                Qualifier = null,
                SemanticId = element.SemanticId?.ToEnvironmentReference_V2_0(),
            };

            if (modelType == ModelType.Property && element is IProperty castedProperty)
                environmentSubmodelElement.submodelElement = new Property_V2_0(submodelElementType)
                {
                    Value = castedProperty.Value?.ToString(),
                    ValueId = castedProperty.ValueId?.ToEnvironmentReference_V2_0(),
                    ValueType = castedProperty.ValueType?.ToString()
                };
            else if (modelType == ModelType.MultiLanguageProperty && element is IMultiLanguageProperty castedMultiLanguageProperty)
            {
                environmentSubmodelElement.submodelElement = new MultiLanguageProperty_V2_0(submodelElementType)
                {
                    Value = castedMultiLanguageProperty.Value,
                    ValueId = castedMultiLanguageProperty.ValueId?.ToEnvironmentReference_V2_0()
                };
            }
            else if (modelType == ModelType.Range && element is IRange castedRange)
            {
                environmentSubmodelElement.submodelElement = new Range_V2_0(submodelElementType)
                {
                    Min = castedRange.Min.ToString(),
                    Max = castedRange.Max.ToString(),
                    ValueType = castedRange.ValueType?.DataObjectType?.Name
                };
            }
            else if (modelType == ModelType.Operation && element is IOperation castedOperation)
            {
                environmentSubmodelElement.submodelElement = new Operation_V2_0(submodelElementType);
                List<OperationVariable_V2_0> inputs = new List<OperationVariable_V2_0>();
                List<OperationVariable_V2_0> outputs = new List<OperationVariable_V2_0>();
                List<OperationVariable_V2_0> inoutputs = new List<OperationVariable_V2_0>();

                if (castedOperation.InputVariables?.Count > 0)
                    foreach (var inputVar in castedOperation.InputVariables)
                        inputs.Add(new OperationVariable_V2_0() { Value = inputVar.Value.ToEnvironmentSubmodelElement_V2_0() });
                if (castedOperation.OutputVariables?.Count > 0)
                    foreach (var outputVar in castedOperation.OutputVariables)
                        outputs.Add(new OperationVariable_V2_0() { Value = outputVar.Value.ToEnvironmentSubmodelElement_V2_0() });
                if (castedOperation.InOutputVariables?.Count > 0)
                    foreach (var inoutputVar in castedOperation.InOutputVariables)
                        inoutputs.Add(new OperationVariable_V2_0() { Value = inoutputVar.Value.ToEnvironmentSubmodelElement_V2_0() });

                (environmentSubmodelElement.submodelElement as Operation_V2_0).InputVariables = inputs;
                (environmentSubmodelElement.submodelElement as Operation_V2_0).OutputVariables = outputs;
                (environmentSubmodelElement.submodelElement as Operation_V2_0).InOutputVariables = inoutputs;
            }
            else if (modelType == ModelType.Event && element is IEvent castedEvent)
                environmentSubmodelElement.submodelElement = new Event_V2_0(submodelElementType) { };
            else if (modelType == ModelType.BasicEvent && element is IBasicEvent castedBasicEvent)
            {
                environmentSubmodelElement.submodelElement = new BasicEvent_V2_0(submodelElementType) 
                { 
                    Observed = castedBasicEvent.Observed.ToEnvironmentReference_V2_0()
                };
            }
            else if (modelType == ModelType.Entity && element is IEntity castedEntity)
            {
                environmentSubmodelElement.submodelElement = new Entity_V2_0(submodelElementType)
                {
                    EntityType = (EnvironmentEntityType)Enum.Parse(typeof(EnvironmentEntityType), castedEntity.EntityType.ToString()),
                    AssetReference = castedEntity.Asset.ToEnvironmentReference_V2_0()
                };

                List<EnvironmentSubmodelElement_V2_0> statements = new List<EnvironmentSubmodelElement_V2_0>();
                if (castedEntity.Statements?.Count > 0)
                    foreach (var smElement in castedEntity.Statements)
                        statements.Add(smElement.ToEnvironmentSubmodelElement_V2_0());
                (environmentSubmodelElement.submodelElement as Entity_V2_0).Statements = statements;
            }
            else if (modelType == ModelType.Blob && element is IBlob castedBlob)
                environmentSubmodelElement.submodelElement = new Blob_V2_0(submodelElementType)
                {
                    Value = Convert.ToBase64String(castedBlob.Value),
                    MimeType = castedBlob.MimeType
                };
            else if (modelType == ModelType.File && element is IFile castedFile)
                environmentSubmodelElement.submodelElement = new File_V2_0(submodelElementType)
                {
                    MimeType = castedFile.MimeType,
                    Value = castedFile.Value
                };
            else if (modelType == ModelType.ReferenceElement && element is IReferenceElement castedReferenceElement)
                environmentSubmodelElement.submodelElement = new ReferenceElement_V2_0(submodelElementType)
                {
                    Value = castedReferenceElement.Value?.ToEnvironmentReference_V2_0()
                };
            else if (modelType == ModelType.RelationshipElement && element is IRelationshipElement castedRelationshipElement)
                environmentSubmodelElement.submodelElement = new RelationshipElement_V2_0(submodelElementType)
                {
                    First = castedRelationshipElement.First?.ToEnvironmentReference_V2_0(),
                    Second = castedRelationshipElement.Second?.ToEnvironmentReference_V2_0()
                };
            else if (modelType == ModelType.AnnotatedRelationshipElement && element is IAnnotatedRelationshipElement castedAnnotatedRelationshipElement)
            {
                environmentSubmodelElement.submodelElement = new AnnotatedRelationshipElement_V2_0(submodelElementType)
                {
                    First = castedAnnotatedRelationshipElement.First?.ToEnvironmentReference_V2_0(),
                    Second = castedAnnotatedRelationshipElement.Second?.ToEnvironmentReference_V2_0(),
                    Annotation = castedAnnotatedRelationshipElement.Annotation?.ToEnvironmentReference_V2_0()
                };
            }
            else if (modelType == ModelType.SubmodelElementCollection && element is ISubmodelElementCollection castedSubmodelElementCollection)
            {
                environmentSubmodelElement.submodelElement = new SubmodelElementCollection_V2_0(submodelElementType);
                List<EnvironmentSubmodelElement_V2_0> environmentSubmodelElements = new List<EnvironmentSubmodelElement_V2_0>();
                if (castedSubmodelElementCollection.Value?.Count > 0)
                    foreach (var smElement in castedSubmodelElementCollection.Value)
                        environmentSubmodelElements.Add(smElement.ToEnvironmentSubmodelElement_V2_0());
                (environmentSubmodelElement.submodelElement as SubmodelElementCollection_V2_0).Value = environmentSubmodelElements;
            }
            else
                return null;

            return environmentSubmodelElement;
        }
    }
}
