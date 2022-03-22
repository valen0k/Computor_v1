#!/usr/bin/env python3

import sys
import re

pattern_error = r"[^\d+-\.=xX]+"
pattern_equations = r"([+|-]?[\d]*[\.]?[\d]*[x]?[\d]*)*=([+|-]?[\d]*[\.]?[\d]*[x]?[\d]*)*"
pattern_equation = r"[+|-]?[\d]*[\.]?[\d]*[x]?[\d]*"


def parse_equation(equations):
    a = b = c = 0.0
    flag = 1
    degree = {}
    for equation in equations:
        for i in equation:
            if 'x' in i:
                arg = i.split('x')
                if arg[1] == '2' and arg[0] != '':
                    a += flag * float(arg[0])
                elif (arg[1] == '1' or arg[1] == '') and arg[0] != '':
                    b += flag * float(arg[0])
                elif arg[1] == '0' and arg[0] != '':
                    c += flag * float(arg[0])
                elif arg[1] != '':
                    if arg[1] in degree:
                        degree[arg[1]] += flag * float(arg[0])
                    else:
                        degree[arg[1]] = flag * float(arg[0])
                else:
                    print("Oooops")
                    exit(1)
            else:
                c += float(i)
        flag = -1
    for key, value in degree.copy().items():
        if value == 0:
            degree.pop(key)
    return print_reduced_form(a, b, c, degree)


def parse(line):
    line = line.replace(' ', '').replace('*', '').replace('^', '')
    res = re.findall(pattern_error, line)
    if res:
        print("Bad symbol")
        exit(1)
    res = re.findall(pattern_equations, line)
    equations = line.split('=')
    if len(equations) != 2 or not res:
        print("Bad equation")
        exit(1)
    left = list(filter(None, re.findall(pattern_equation, equations[0])))
    right = list(filter(None, re.findall(pattern_equation, equations[1])))
    return parse_equation([left, right])


def print_reduced_form(a, b, c, degree):
    print("Reduced form:", end=' ')
    if c != 0:
        if c < 0:
            print("-", c * -1, "* X^0", end=' ')
        else:
            print(c, "* X^0", end=' ')
    if b != 0:
        if b < 0:
            print("-", b * -1, "* X^1", end=' ')
        elif c != 0:
            print("+", b, "* X^1", end=' ')
        else:
            print(b, "* X^1", end=' ')
    if a != 0:
        if a < 0:
            print("-", a * -1, "* X^2", end=' ')
        elif c != 0 or b != 0:
            print("+", a, "* X^2", end=' ')
        else:
            print(a, "* X^2", end=' ')
    if degree:
        for key, value in degree.items():
            if value < 0:
                print("- ", value * -1, " * X^", key, end=' ', sep='')
            elif a != 0 or b != 0 or c != 0:
                print("+ ", value, " * X^", key, end=' ', sep='')
            else:
                print(value, " * X^", key, end=' ', sep='')
        print("= 0")
        print("Polynomial degree:", end=' ')
        for i in degree:
            print(i, end=' ')
        print()
        print("The polynomial degree is strictly greater than 2, I can't solve.")
        exit(1)
    print("= 0")
    return a, b, c


def solving2_degree(a, b, c):
    discriminant = b ** 2 - 4 * a * c
    if discriminant == 0:
        print("Polynomial degree: 1")
        print("Discriminant is 0, the single solution is:")
        print("D = b^2 - 4ac = {0}^2 - 4 * {1} * {2} = {3}".format(b, a, c, discriminant))
        print("X = -b / 2a = -1 * {} / (2 * {})".format(b, a))
        x = -1 * b / (2 * a)
        print("X = {:.6f}".format(x))
    else:
        print("Polynomial degree: 2")
        if discriminant > 0:
            print("Discriminant is strictly positive, the two solutions are:")
        else:
            print("Discriminant is strictly negative, the two complex solutions are:")
        print("D = b^2 - 4ac = {0}^2 - 4 * {1} * {2} = {3}".format(b, a, c, discriminant))
        print(u"X1,2 = (-b \u00B1 \u221aD) / 2a")
        x = (-1 * b - discriminant ** (1 / 2)) / (2 * a)
        print("X1 = {:.6f}".format(x))
        x = (-1 * b + discriminant ** (1 / 2)) / (2 * a)
        print("X2 = {:.6f}".format(x))


def solving1_degree(b, c):
    print("Polynomial degree: 1")
    print("The solution is:")
    print("X = -c / b = -1 * {} / {} = {:.6f}".format(c, b, -1 * c / b))


def solving(line):
    a, b, c = parse(line)
    if a != 0:
        solving2_degree(a, b, c)
    elif b != 0:
        solving1_degree(b, c)
    elif c != 0:
        print("There is no solution")
    else:
        print("Any real number is a solution!")


if __name__ == "__main__":
    if len(sys.argv) == 2:
        solving(sys.argv[1].lower())
    else:
        print("Need 2 args")
