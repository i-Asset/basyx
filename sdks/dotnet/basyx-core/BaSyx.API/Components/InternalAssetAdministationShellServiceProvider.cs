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

namespace BaSyx.API.Components
{
    internal sealed class InternalAssetAdministationShellServiceProvider : AssetAdministrationShellServiceProvider
    {

        internal InternalAssetAdministationShellServiceProvider(IAssetAdministrationShell aas) : base(aas)
        {
            AssetAdministrationShell = aas;
        }

        public override IAssetAdministrationShell AssetAdministrationShell { get; protected set; }

        public override IAssetAdministrationShell GenerateAssetAdministrationShell()
        {
            return AssetAdministrationShell;
        }
    }
}
