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
using Newtonsoft.Json;
using System;
using System.Runtime.Serialization;

namespace BaSyx.Models.Connectivity
{
    public class HttpEndpoint : IEndpoint
    {
        public string Address { get; }

        [IgnoreDataMember]
        public Uri Url { get; }

        public string Type => EndpointType.HTTP;

        public IEndpointSecurity Security { get; set;}

        [JsonConstructor]
        public HttpEndpoint(string address)
        {
            address = address ?? throw new ArgumentNullException(nameof(address));
            Url = new Uri(address);
            Address = Url.ToString();
        }

        public HttpEndpoint(Uri uri) : this(uri?.ToString())
        { }
    }
}
