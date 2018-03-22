package demo

final class MyJestTestFramework extends sjest.JestFramework {
  override protected def optJsPath: String = "./out/client/test/fastOpt/dest/out.js"
  override protected def testJsDir: String = "./out/client/scala-jests/"
}
