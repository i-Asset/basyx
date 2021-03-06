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
using System.Net;
using System.Text;

namespace BaSyx.Utils.Client.Http
{
    public class LegacyHttpClient
    {
        private static readonly JsonSerializerSettings jsonSerializerSettings;

        static LegacyHttpClient()
        {
            jsonSerializerSettings = new JsonSerializerSettings() { NullValueHandling = NullValueHandling.Ignore, Formatting = Formatting.Indented };
        }

        public HttpWebRequest CreateRequest(Uri uri, string method, object content)
        {
            var request = WebRequest.CreateHttp(uri);
            request.Method = method;
            request.Accept = "application/json";

            if (content != null)
            {
                string body = JsonConvert.SerializeObject(content, jsonSerializerSettings);
                if (!string.IsNullOrEmpty(body))
                {
                    var payload = Encoding.ASCII.GetBytes(body);
                    request.ContentType = "application/json";
                    request.ContentLength = payload.Length;

                    using (var stream = request.GetRequestStream())
                    {
                        stream.Write(payload, 0, payload.Length);
                    }
                }
            }
            return request;
        }

        private HttpWebResponse SendRequest(HttpWebRequest request)
        {
            try
            {
                var response = (HttpWebResponse)request.GetResponse();
                return response;
            }
            catch (Exception e)
            {
                Console.Out.WriteLine(e.Message);
                if (e is WebException)
                    return (HttpWebResponse)((WebException)e).Response;
                else if (e.InnerException != null && e.InnerException is WebException)
                    return (HttpWebResponse)((WebException)e.InnerException).Response;
                return null;
            }
        }
    }
}
