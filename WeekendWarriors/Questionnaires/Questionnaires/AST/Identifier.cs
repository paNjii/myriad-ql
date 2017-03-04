﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.Messages;
using System.Diagnostics;
using Questionnaires.SemanticAnalysis;
using Questionnaires.Value;

namespace Questionnaires.AST
{
    public class Identifier : IExpression
    {
        public Identifier(string name)
        {
            this.Name = name;
        }

        public string Name
        {
            get;
        }        

        public bool CheckSemantics(QLContext context, List<Message> messages)
        {
            if (!context.ContainsQuestion(Name))
            {
                messages.Add(new Error(string.Format("Invalid use of undefined identifier {0}", Name)));
                return false;
            }
            
            return true;
        }

        public IValue GetResultType(QLContext context)
        {
            return context.GetQuestionType(Name);
        }
    }
}
