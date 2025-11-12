def recursive_fibonacci(n): 
    if n <= 1: 
        return n 
    else: 
        return recursive_fibonacci(n-1) + recursive_fibonacci(n-2) 
 
def non_recursive_fibonacci(n): 
    first = 0 
    second = 1 
    print(first)
    if n > 1:
        print(second)
    for i in range(2, n):
        third = first + second 
        print(third)
        first, second = second, third 
 
if __name__ == "__main__": 
    n = int(input("Enter how many terms you want: "))

    print("\nFibonacci Series using Recursive Method:")
    for i in range(n): 
        print(recursive_fibonacci(i))
    
    print("\nFibonacci Series using Non-Recursive (Iterative) Method:")
    non_recursive_fibonacci(n)
