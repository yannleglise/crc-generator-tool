#/bin/sh

#################################################
# Lauching script for LYS CRC-64 Generator Tool #
#################################################

# Check if JAVA_HOME is set properly
if [ "${JAVA_HOME}foo" == "foo" ]
then
    echo "JAVA_HOME environment variable is not set. This program requires it to refer to a java 8 installation"
    exit 1
fi

# Check the java executable is present and executable
JAVA_BINARY=${JAVA_HOME}/bin/java
if  [ ! -x ${JAVA_BINARY} ]
then
    echo Java executable ${JAVA_BINARY} does not exist or cannot be executed
    exit 1
fi

# Check the java version
JAVA_VER_MAJOR=0
JAVA_VER_TEXT=`java -version 2>&1 | grep -i version`
for token in `java -version 2>&1 | grep -i version | tr ' ' '\n'`
do
    echo $token | grep '"[0-9][0-9]*\.[0-9][0-9]*\.[0-9][0-9]*"' > /dev/null
    rc=$?
    if [ ${rc} -eq 0 ]
    then
        JAVA_VER_MAJOR=`echo ${token} | tr '".' '\n' | head -2 | tail -1`
        break
    fi
done

if  [ ${JAVA_VER_MAJOR} -lt 8 ]
then
   echo Java version needs to be at least 8, but version ${JAVA_VER_MAJOR} is present
   exit 1
fi

THIS_SCRIPT_FILE=`\readlink -f "$0" `
CRC64TOOL_BASE_DIRECTORY=`dirname "${THIS_SCRIPT_FILE}"`

CRC64TOOL_LIB_DIR=${CRC64TOOL_BASE_DIRECTORY}/bin

# Buils the classpath
CRC64TOOL_CLASSPATH=
for file in `\ls -1 ${CRC64TOOL_LIB_DIR}/*.jar`
do
    filename=`basename $file`
    CRC64TOOL_CLASSPATH="${CRC64TOOL_LIB_DIR}/${filename}:${CRC64TOOL_CLASSPATH}"
done

# Define the main class
CRC64TOOL_MAIN_CLASS=org.lys.apps.crcgenerator.CrcGeneratorTool

# Start the tool
${JAVA_BINARY} -cp "${CRC64TOOL_CLASSPATH}" ${CRC64TOOL_MAIN_CLASS}

