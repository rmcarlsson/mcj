@rem Generate the Java# code for .proto files

setlocal

@rem enter this directory
cd /d %~dp0

rmdir /q /s gen
mkdir gen\se\trantor\grpcproto


set TOOLS_PATH=C:\Users\rmcar\Source\Repos\grainsim\packages\Google.Protobuf.Tools.3.0.0\tools\windows_x64
#set TOOLS_PATH=C:\Users\carltmik\grpc2\examples\csharp\helloworld\packages\Google.Protobuf.3.0.0-beta2\tools

set PLUGIN_PATH=C:\Users\rmcar\.m2\repository\io\grpc\protoc-gen-grpc-java\0.15.0
set PLUGIN=protoc-gen-grpc-java-0.15.0-windows-x86_64.exe

set PROTOBUF_IMPORT=C:\Users\rmcar\Source\Repos\grainsim\packages\Google.Protobuf.Tools.3.0.0-beta3\tools

%TOOLS_PATH%\protoc.exe --java_out=gen --proto_path=. --proto_path=%PROTOBUF_IMPORT% mc-service.proto
%TOOLS_PATH%\protoc.exe --proto_path=. --proto_path=%PROTOBUF_IMPORT% --plugin=protoc-gen-grpc-java=%PLUGIN_PATH%\%PLUGIN% --grpc-java_out=gen   mc-service.proto


endlocal