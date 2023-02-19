# (simple) Cryptography algorithms implemented in scala

The goal of this project is to implement all the algorithms from an undergraduate level mathematics cryptography
class.

Every algorithm will come with explanations on how they work, and how they have been implemented. As a challenge, I will
also try to do this project using rust as a means of learning the language.

# Sample usage of CLI tool

1. Using hill cypher, encrypt the string `"hello"`: `hc --string=hello`
2. Using hill cypher, encrypt a file, and save the encrypted
   file: `hc --in-path="src/test/scala/hamlet.txt" --out-path="src/test/scala/hamlet_encrypted.txt"`

# Currently supported algorithms

1. [Hill Cypher](src/main/notes/algorithms/hill-cypher.md)

## Coming Soon

- [ ] Decryption
- [ ] Randomized keys
- [ ] Key as input
- [ ] Another algorithm

## Project structure

The main folders in this project are:

`src/main/scala/algorithms`, here is the code for all the algorithms

There is also `src/main/scala/math`, since this project main objective was to learn, I decided that it would be better
to use no math libs, and to implement as much as possible here.

`src/main/notes`, here is a companion md file for all classes in `src/main/scala/math` and also for all algorithms
in `src/main/scala/algorithms`

```
...
├── README.md
├── src
│   ├── main
│   │   ├── notes
│   │   │   └── algorithms
│   │   │       └── hill-cypher.md
│   │   └── scala
│   │       ├── algorithms
│   │       │   ├── EncryptionAlgo.scala
│   │       │   └── HillCypher.scala
│   │       ├── cli
│   │       │   └── HillCypherCommand.scala
│   │       ├── Main.scala
│   │       └── math
│   │           ├── Congruence.scala
│   │           └── Matrix.scala
│   └── test
│       └── scala
│           ├── algorithms
│           │   └── HillCypherTest.scala
│           ├── hamlet.txt
│           ├── math
│           │   ├── CongruenceTest.scala
│           │   └── MatrixTest.scala
│           └── txt.txt
```
