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
using System.Collections.Generic;
using System.Threading.Tasks;

namespace BaSyx.Utils.ResultHandling
{
    public class OperationResult : Result
    {
        public OperationResult(bool success) : base(success)
        { }
        public OperationResult(Exception e) : base(e)
        { }
        public OperationResult(bool success, IMessage message) : base(success, message)
        { }
        [JsonConstructor]
        public OperationResult(bool success, List<IMessage> messages) : base(success, messages)
        { }

        public static implicit operator Task<OperationResult>(OperationResult operationResult)
        {
            return Task.FromResult(operationResult);
        }

        public static explicit operator OperationResult(Task<OperationResult> taskOperationResult)
        {
            return taskOperationResult.Result;
        }
    }
}
