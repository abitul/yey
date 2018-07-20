// Place your Spring DSL code here
import apifutsal.*

beans = {
    imageEncrypter(ImageEncrypter)
    randomGenerator(RandomGenerator)
    mySecurityEventListener(MySecurityEventListener)
    accessTokenJsonRenderer(FutsalAppRestAuthTokenJsonRenderer)
}
