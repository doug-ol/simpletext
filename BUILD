java_binary(
    name = "ProjectRunner",
    srcs = glob(["src/main/java/com/example/ProjectRunner.java"]),
    deps = [":greeter"],
)

java_library(
    name = "greeter",
    srcs = ["src/main/java/com/example/Greeting.java"],
    visibility = ["//src/main/java/com/example/cmdline:__pkg__"],
    deps=["@maven//:org_apache_lucene_lucene_core", "@maven//:org_apache_lucene_lucene_codecs"]
)
