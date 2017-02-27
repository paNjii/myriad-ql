package org.ql.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.ql.ast.*;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionText;
import org.ql.ast.type.*;
import org.ql.grammar.QLParser;
import org.ql.grammar.QLVisitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AntlrVisitor extends AbstractParseTreeVisitor<Node> implements QLVisitor<Node> {

    @Override
    public Node visitForm(QLParser.FormContext ctx) {
        List<Statement> statements = new ArrayList<>();

        for (QLParser.StatementContext statementContext : ctx.statement()) {
            statements.add((Statement) visit(statementContext));
        }

        return new Form((Identifier) visit(ctx.id), statements).setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitQuestion(QLParser.QuestionContext ctx) {
        AbstractNode question = new Question(
                (Identifier) visit(ctx.id),
                (QuestionText) visit(ctx.text),
                (Type) visit(ctx.type()),
                ctx.defaultValue() == null ? null : (Expression) visit(ctx.defaultValue())
        );

        return question.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitIfThen(QLParser.IfThenContext ctx) {
        AbstractNode ifThen = new IfThen(
                (Expression) visit(ctx.expression()),
                createStatements(ctx.thenStatements)
        );

        return ifThen.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitIfElseThen(QLParser.IfElseThenContext ctx) {
        AbstractNode ifThenElse = new IfThenElse(
                (Expression) visit(ctx.expression()),
                createStatements(ctx.thenStatements),
                createStatements(ctx.elseStatements)
        );

        return ifThenElse.setMetadata(extractMetadata(ctx));
    }

    private List<Statement> createStatements(List<QLParser.StatementContext> statementsContext) {
        List<Statement> thenStatements = new ArrayList<>();

        for (QLParser.StatementContext statementContext : statementsContext) {
            thenStatements.add((Statement) visit(statementContext));
        }
        return thenStatements;
    }

    @Override
    public Node visitQuestionText(QLParser.QuestionTextContext ctx) {
        AbstractNode questionText = new QuestionText(removeQuotes(ctx.getText()));

        return questionText.setMetadata(extractMetadata(ctx));
    }

    private java.lang.String removeQuotes(java.lang.String text) {
        return text.substring(1, text.length() - 1);
    }

    @Override
    public Node visitDefaultValue(QLParser.DefaultValueContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Node visitDecimalLiteral(QLParser.DecimalLiteralContext ctx) {
        AbstractNode decimalLiteral = new DecimalLiteral(new BigDecimal(ctx.DECIMAL_LITERAL().getText()));

        return decimalLiteral.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitNegation(QLParser.NegationContext ctx) {
        AbstractNode negation = new Negation((Expression) visit(ctx.expression()));

        return negation.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitProduct(QLParser.ProductContext ctx) {
        AbstractNode product = new Product((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        return product.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitIncrement(QLParser.IncrementContext ctx) {
        AbstractNode increment = new Increment((Expression) visit(ctx.expression()));

        return increment.setMetadata(new Metadata(ctx.start.getLine(), ctx.start.getCharPositionInLine()));
    }

    @Override
    public Node visitSubtraction(QLParser.SubtractionContext ctx) {
        AbstractNode subtraction = new Subtraction((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        return subtraction.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitNotEqual(QLParser.NotEqualContext ctx) {
        AbstractNode notEqual = new NotEqual((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        return notEqual.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitLogicalAnd(QLParser.LogicalAndContext ctx) {
        AbstractNode logicalAnd = new LogicalAnd((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        return logicalAnd.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitLowerThan(QLParser.LowerThanContext ctx) {
        AbstractNode lowerThan = new LowerThan((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        return lowerThan.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitGreaterThanOrEqual(QLParser.GreaterThanOrEqualContext ctx) {
        GreaterThanOrEqual greaterThanOrEqual = new GreaterThanOrEqual((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        return greaterThanOrEqual.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitDivision(QLParser.DivisionContext ctx) {
        Division division = new Division((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        return division.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitParameter(QLParser.ParameterContext ctx) {
        return new Parameter(new Identifier(ctx.ID().getText()));
    }

    @Override
    public Node visitIdentifier(QLParser.IdentifierContext ctx) {
        return new Identifier(ctx.ID().getText()).setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitBooleanLiteral(QLParser.BooleanLiteralContext ctx) {
        BooleanLiteral booleanLiteral = new BooleanLiteral(Boolean.parseBoolean(ctx.BOOLEAN_LITERAL().getText()));

        return booleanLiteral.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitGroup(QLParser.GroupContext ctx) {
        return new Group((Expression) visit(ctx.expression())).setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitAddition(QLParser.AdditionContext ctx) {
        Addition addition = new Addition((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        return addition.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitGreaterThan(QLParser.GreaterThanContext ctx) {
        GreaterThan greaterThan = new GreaterThan((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        return greaterThan.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitStringLiteral(QLParser.StringLiteralContext ctx) {
        StringLiteral stringLiteral = new StringLiteral(removeQuotes(ctx.STRING_LITERAL().getText()));

        return stringLiteral.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitDecrement(QLParser.DecrementContext ctx) {
        Decrement decrement = new Decrement((Expression) visit(ctx.expression()));

        return decrement.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitEquals(QLParser.EqualsContext ctx) {
        Equals equals = new Equals((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        return equals.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitLowerThanOrEqual(QLParser.LowerThanOrEqualContext ctx) {
        LowerThanOrEqual lowerThanOrEqual = new LowerThanOrEqual((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        return lowerThanOrEqual.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
        IntegerLiteral integerLiteral = new IntegerLiteral(Integer.parseInt(ctx.INTEGER_LITERAL().getText()));

        return integerLiteral.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitLogicalOr(QLParser.LogicalOrContext ctx) {
        LogicalOr logicalOr = new LogicalOr((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        return logicalOr.setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitTypeBoolean(QLParser.TypeBooleanContext ctx) {
        return new BooleanType().setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitTypeFloat(QLParser.TypeFloatContext ctx) {
        return new FloatType().setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitTypeInteger(QLParser.TypeIntegerContext ctx) {
        return new IntegerType().setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitTypeString(QLParser.TypeStringContext ctx) {
        return new StringType().setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitTypeMoney(QLParser.TypeMoneyContext ctx) {
        return new MoneyType().setMetadata(extractMetadata(ctx));
    }

    @Override
    public Node visitTypeDate(QLParser.TypeDateContext ctx) {
        return new DateType().setMetadata(extractMetadata(ctx));
    }

    private Metadata extractMetadata(ParserRuleContext context) {
        return new Metadata(context.start.getLine(), context.start.getCharPositionInLine());
    }
}
