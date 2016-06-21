@rem Generate the Java# code for .proto files

setlocal

@rem enter this directory
cd /d %~dp0

rmdir /q /s gen
mkdir gen\se\trantor\grpcproto

set PLUGIN=protoc-gen-grpc-java-0.14.0-windows-x86_32.exe
set TOOLS_PATH=C:\Users\carltmik\grpc2\examples\csharp\helloworld\packages\Google.Protobuf.3.0.0-beta2\tools
set PLUGIN_PATH=C:\Users\carltmik\.m2\repository\io\grpc\protoc-gen-grpc-java\0.14.0
set PROTOBUF_IMPORT=C:\Users\carltmik\protobuf\src

%TOOLS_PATH%\protoc.exe --java_out=gen --proto_path=. --proto_path=%PROTOBUF_IMPORT% mc-service.proto
%TOOLS_PATH%\protoc.exe --proto_path=. --proto_path=%PROTOBUF_IMPORT% --plugin=protoc-gen-grpc-java=%PLUGIN_PATH%\%PLUGIN% --grpc-java_out=gen   mc-service.proto


endlocal