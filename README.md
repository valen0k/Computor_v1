# Computor_v1

## Task

Write a program that solves a polynomial second or lower degree equation. You will have to show at least:

* The equation in its reduced form.
* The degree of the equation.
* It’s solution(s) and the polarity of the discriminant if it makes sens.

Ex examples:

```shell
$ ./computor "5 * X^0 + 4 * X^1 - 9.3 * X^2 = 1 * X^0"
Reduced form: 4 * X^0 + 4 * X^1 - 9.3 * X^2 = 0
Polynomial degree: 2
Discriminant is strictly positive, the two solutions are:
0.905239
-0.475131
```

We will always expect the entry to have the right format, ie. every term respect the form a ∗ x^p.
Exponents are organized and present.
Beware, it doesn’t mean the equation has a solution!
If so, your program should detect it and specify it. 
You should also think of zero, negative or non whole coefficients...

There might be exceptions you will have to manage. 
In the equation 42∗X^0 = 42∗X^0, for instance, each real number is a solution...

Here is a bonus list you might want to implement:

* Manage entry mistakes (vocabulary and syntax).
* Manage free form entrie.

```shell
$ ./computor "5 + 4 * X + X^2= X^2"
Reduced form: 5 + 4 * X = 0
Polynomial degree: 1
The solution is:
-1.25
```
* Display solution(s) as an irreducible fraction if it’s interesting.
* Display the intermediate steps.
* ...

### Instructions
* Think of complex solution when the degree equals 2.
* You’re free to pick your favorite language.
* That being said, you obviously cannot use a math lib function (except for subtraction, division, addition and multiplication of real numbers) that you would not have implemented yourself.
* If you work in a compilable language, you will present a Makefile that includes the usual set of rules.
