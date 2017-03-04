﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis;
using Questionnaires.SemanticAnalysis.Messages;
using Questionnaires.Value;

namespace Questionnaires.AST.Operators
{
    public class GreaterThanOrEqual : Binary
    {
        public GreaterThanOrEqual(IExpression lhs, IExpression rhs) : base(lhs, rhs)
        {
        }
        

        public override IValue GetResultType(QLContext context)
        {
            return Lhs.GetResultType(context).GreaterThanOrEqual(Rhs.GetResultType(context));
        }
    }
}
