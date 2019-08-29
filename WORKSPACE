workspace(name = "com_mongodb_simpletext")
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

RULES_JVM_EXTERNAL_TAG = "2.7"
RULES_JVM_EXTERNAL_SHA = "f04b1466a00a2845106801e0c5cec96841f49ea4e7d1df88dc8e4bf31523df74"

http_archive(
    name = "rules_jvm_external",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    sha256 = RULES_JVM_EXTERNAL_SHA,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

LUCENE_VERSION = "8.1.0"
LUCENE_ARTIFACTS = [
    "org.apache.lucene:lucene-analyzers-common:" + LUCENE_VERSION,
    "org.apache.lucene:lucene-core:" + LUCENE_VERSION,
    "org.apache.lucene:lucene-highlighter:" + LUCENE_VERSION,
    "org.apache.lucene:lucene-queryparser:" + LUCENE_VERSION,
    "org.apache.lucene:lucene-codecs:" + LUCENE_VERSION,
]
maven_install(
    artifacts = LUCENE_ARTIFACTS,
    repositories = [
        "https://repo1.maven.org/maven2"
    ],
    fetch_sources = True,
)

