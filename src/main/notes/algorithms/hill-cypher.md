# Hill Cypher algorithm

The hill cypher algorithm generates a unique encryption for every pair (or group) of characters. This is a big
improvement over a simple substitution algorithm, as we are no longer vulnerable to simple frequency attacks. This is
because any letter, say 'e', will not always be encrypted to the same character.

For this cypher, the key is a 2x2 matrix. If you wanted to improve security, you could use a 3x3 matrix and group the
string into 3's, rather than 2's.

## Encryption

Let's say that we want to encrypt the pair ('h', 'e') with the following key:

```math
\begin{pmatrix}

3 & 3 \\
2 & 5 

\end{pmatrix}
```

We would first get the ascii values of the characters, and the pair would now be (104, 101). Because we dont want to encrypt the first 32 ascii characters (because they are special charcters such as backspace), we will substract 32 from the vector, giving us (72, 69).

Now, to get their encrypted value, we would multiply it by the matrix: 


```math
\begin{pmatrix}

3 & 3 \\
2 & 5 

\end{pmatrix}

\cdot

\begin{pmatrix}

72 \\
69 

\end{pmatrix}


= 

\begin{pmatrix}

423 \\
489 

\end{pmatrix}

```

Now, we find the result mod 95 (as there are (127 - 32) 95 characters in the ascii table ignoring the first 32 special characters)

```math

\begin{pmatrix}

423 \\
489 

\end{pmatrix}

\mod 95

= 

\begin{pmatrix}

43 \\
14 

\end{pmatrix}

```


and to the encrypted pair is (43, 14), we first need to add 32, giving us (75, 46), and then convert it to characters: (K, .)

If we had however, encrypted the pair (h, i) instead of (h, e), we would have gotten (W, B). This shows that the same letter will not always give the same output. 


## Decryption 

Decrypting involves the same process, but using the inverse of the key. You can find the inverse of a 2x2 matrix mod 95 in the following way: 

First, calculate the determinant: 

```math
det(\begin{pmatrix}
3 & 3 \\
2 & 5 
\end{pmatrix})

= (3 * 5) - (3 * 2) = 9
```

The determinant was calculated by (top left * bottom right) - (top right * bottom left). 

Next, we need to find the (modular) inverse of 9 mod 95. To find the inverse, we must solve the following congruence: 

```math

9x \equiv 1 \mod{95}

```

The smallest natural number that satisfies the above will be the inverse of 9, in this case, the inverse of 9 is 74.

Next, we swap the top right of the matrix with the bottom left, and multiply the top right and the bottom left by -1. Then we will multily every element of the matrix by 74. 

```math

74 \cdot

\begin{pmatrix}

5 & -3 \\
-2 & 3 

\end{pmatrix}

=

\begin{pmatrix}

370  & -222 \\
-148 & 222 

\end{pmatrix}

\equiv 

\begin{pmatrix}

85  & 63 \\
42 & 32 

\end{pmatrix}


\mod{95}
```

Like that, we have the key, now to show that we can use it for decryption, lets multiply (K, .) by the matrix we just got: 


```math
\begin{pmatrix}

85  & 63 \\
42 & 32 

\end{pmatrix}

\cdot

\begin{pmatrix}

75 - 32 \\ 
46 - 32   

\end{pmatrix}


=    

\begin{pmatrix}

85  & 63 \\
42 & 32 

\end{pmatrix}

\cdot

\begin{pmatrix}

43 \\ 
14  

\end{pmatrix}

=

\begin{pmatrix}

4,537 \\
2,254 

\end{pmatrix}

```

Now, we need to substract 32 from all entries in the final vector, and then map every elment to itself mod 95, and add the 32 back.  

```math

\begin{pmatrix}

4,505 \\
2,222 

\end{pmatrix}

\equiv 

\begin{pmatrix}

4,505 \\
2,222 

\end{pmatrix}

\mod{95}

=

\begin{pmatrix}

40 \\
37 

\end{pmatrix}

```

```math

\begin{pmatrix}

40 \\
37 

\end{pmatrix}

+ 

\begin{pmatrix}

32 \\
32 

\end{pmatrix}

= 

\begin{pmatrix}

72 \\
69 

\end{pmatrix}
```


72 and 69 represent the ascii characters 'h' and 'e', which were precisely the characters we had encrypted. Therefore, the decryption has worked! 

## Notes

Be careful when choosing a key, as they wont all have inverses mod n. 

When dealing whith 2x2 matrices, you can see if they have an inverse in the following way: 

Take the following matrix: 

```math
\begin{pmatrix}

a  & b \\
x & y 

\end{pmatrix}
```

Its deternminant is given by $ay - bx$. Define $k = ay - bx$. 

For the matrix to exist, k must have an inverse mod n, meaning that: 

```math
kx \equiv 1 \mod{n}
```

for some $x \in \mathbb{N}$. We can re write the above equation as: 

```math
kx + nq = 1
```

for some $x \in \mathbb{N}$ and $q \in \mathbb{Z}$ (note that we do not care what the value of q is). 

The above equation will only have a solution/s if the greatest common divisor of k and n divides 1. Meaning that the matrix does not have an inverse mod n if k and q have any common divisors. 
