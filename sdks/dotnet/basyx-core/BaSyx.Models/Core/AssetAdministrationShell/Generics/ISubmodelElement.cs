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
using BaSyx.Models.Core.AssetAdministrationShell.Identification;
using BaSyx.Models.Core.AssetAdministrationShell.Constraints;
using Newtonsoft.Json;
using BaSyx.Models.Extensions;
using BaSyx.Models.Core.AssetAdministrationShell.Semantics;
using BaSyx.Models.Core.Common;

namespace BaSyx.Models.Core.AssetAdministrationShell.Generics
{
    [JsonConverter(typeof(SubmodelElementConverter))]
    public interface ISubmodelElement : IHasSemantics, IQualifiable, IReferable, IHasKind, IModelElement, IHasDataSpecification
    { }
}
