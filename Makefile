all: execJDBCLocal

testJDBCLocal:
	javac -d lib -classpath "lib/*" -sourcepath src src/TestJDBCLocal.java

# Execution:
execJDBCLocal: testJDBCLocal
	java -classpath "lib:lib/*" TestJDBCLocal