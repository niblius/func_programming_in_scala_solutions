def unsized: SGen[A] =
  SGen(_ => this)