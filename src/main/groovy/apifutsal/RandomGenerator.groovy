package apifutsal

class RandomGenerator{

        def generator = { String alphabet, int n ->
           return new Random().with {
                (1..n).collect { alphabet[ nextInt( alphabet.length() ) ] }.join()
            }
        }
}


