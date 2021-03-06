case class Reader[R, A](run: R => A)

object Reader {
  def readerMonad[R] = new Monad[({type f[x] = Reader[R,x]})] {
    def unit[A](a: => A): Reader[R,A] =
      Reader(_: R => a)
    
    def flatMap[A,B](st: Reader[R,A])(f: A => Reader[R,B]): Reader[R,B] =
      Reader(r => f(st.run(r)).run(r))
  }

  def getR[R](r: Reader): Reader[R,R] = Reader(r => r)
}