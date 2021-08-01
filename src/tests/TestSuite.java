package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.graph.inputs.GraphByArray;
import tests.graph.inputs.GraphByFile;
import tests.graph.inputs.GraphByString;

@RunWith(Suite.class)
@SuiteClasses({
	GraphByArray.class,
	GraphByString.class,
	GraphByFile.class
})
public class TestSuite {}
