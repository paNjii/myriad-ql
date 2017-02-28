package test.org.uva.taxfree.ast;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uva.taxfree.ast.Ast;
import org.uva.taxfree.main.SemanticsAnalyzer;

import java.io.File;

/**
 * Created by Alex on 20-2-2017.
 */
public class SemanticsAnalyzerTest {
    @Test
    public void testHasDuplicateQuestionLabels() throws Exception {
        Ast ast = Ast.generateAst(new File("src\\test\\org\\uva\\taxfree\\ast\\semanticErrors\\duplicateQuestionLabelForm.txt"));
        SemanticsAnalyzer semanticsAnalyzer = new SemanticsAnalyzer(ast);
        Assert.assertFalse(semanticsAnalyzer.validSemantics(), "Duplicate question label, so test should fail");
        Assert.assertEquals(semanticsAnalyzer.getSemanticErrors().size(), 1, "We only have one duplicate here");
    }

    @Test
    public void testHasMultipleDuplicateQuestionLabels() throws Exception {
        Ast ast = Ast.generateAst(new File("src\\test\\org\\uva\\taxfree\\ast\\semanticErrors\\duplicateQuestionLabelsForm.txt"));
        SemanticsAnalyzer semanticsAnalyzer = new SemanticsAnalyzer(ast);
        Assert.assertFalse(semanticsAnalyzer.validSemantics(), "Duplicate question label, so test should fail");
        Assert.assertEquals(semanticsAnalyzer.getSemanticErrors().size(), 2, "We should have three duplicates here");
    }

    @Test
    public void testHasDuplicateQuestionIds() throws Exception {
        Ast ast = Ast.generateAst(new File("src\\test\\org\\uva\\taxfree\\ast\\semanticErrors\\duplicateQuestionIdForm.txt"));
        SemanticsAnalyzer semanticsAnalyzer = new SemanticsAnalyzer(ast);
        Assert.assertFalse(semanticsAnalyzer.validSemantics(), "Duplicate question id, so test should fail");
        Assert.assertEquals(semanticsAnalyzer.getSemanticErrors().size(), 1, "We only have one duplicate here");
    }

    @Test
    public void testHasMultipleDuplicateQuestionIds() throws Exception {
        Ast ast = Ast.generateAst(new File("src\\test\\org\\uva\\taxfree\\ast\\semanticErrors\\duplicateQuestionIdsForm.txt"));
        SemanticsAnalyzer semanticsAnalyzer = new SemanticsAnalyzer(ast);
        Assert.assertFalse(semanticsAnalyzer.validSemantics(), "Duplicate question id, so test should fail");
        Assert.assertEquals(semanticsAnalyzer.getSemanticErrors().size(), 2, "We should have three duplicates here");
    }

    @Test
    public void testHasDuplicateQuestionIdsAndLabels() throws Exception {
        Ast ast = Ast.generateAst(new File("src\\test\\org\\uva\\taxfree\\ast\\semanticErrors\\duplicateQuestionIdsAndLabelsForm.txt"));
        SemanticsAnalyzer semanticsAnalyzer = new SemanticsAnalyzer(ast);
        Assert.assertFalse(semanticsAnalyzer.validSemantics(), "Duplicate question id, so test should fail");
        Assert.assertEquals(semanticsAnalyzer.getSemanticErrors().size(), 4, "We should have four duplicates here");
    }

}