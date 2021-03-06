package inheritance;

import inheritance.memberaccess.SimpleValue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleValueTest{
    @Test
    public void testPrivateAccess() throws Exception {
        SimpleValue a = new SimpleValue(10);
        SimpleValue b = new SimpleValue(a);
        Assert.assertTrue(a.isSameAs(b), "Should be equal!");
    }

    int mValue;

    boolean equals(SimpleValue b){
        return mValue == b.getValue();
    }

}