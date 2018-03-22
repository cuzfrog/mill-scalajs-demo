package demo

final class MyJestTestFramework extends sjest.JestFramework {
  override protected def optJsPath: String = "./out/client/fastOpt/dest/out.js"
  override protected def testJsDir: String = "./out/scala-jests/"
}
