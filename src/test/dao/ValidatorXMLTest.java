package dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorXMLTest {
    @Test
    public void validatorXML() throws Exception {
        boolean [] expected={true,false};
        boolean [] actual={false,true};
        String path = "D:\\work\\java3.3\\web\\data\\";
        actual[0]=ValidatorXML.validatorXML(path + "postcards.xml", path + "postcards.xsd");
        actual[1]=ValidatorXML.validatorXML(path + "postcards.xml", path + "test.xsd");
        assertArrayEquals(expected,actual);
    }

}