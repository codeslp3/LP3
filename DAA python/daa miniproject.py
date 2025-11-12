import threading
import time
import random

def sequential_multiply(a, b):
    rows_a, cols_a, cols_b = len(a), len(a[0]), len(b[0])
    result = [[0] * cols_b for _ in range(rows_a)]
    for i in range(rows_a):
        for j in range(cols_b):
            for k in range(cols_a):
                result[i][j] += a[i][k] * b[k][j]
    return result

def thread_per_row(a, b):
    rows_a, cols_a, cols_b = len(a), len(a[0]), len(b[0])
    result = [[0] * cols_b for _ in range(rows_a)]

    def compute_row(row):
        for j in range(cols_b):
            for k in range(cols_a):
                result[row][j] += a[row][k] * b[k][j]

    threads = [threading.Thread(target=compute_row, args=(i,)) for i in range(rows_a)]
    for t in threads:
        t.start()
    for t in threads:
        t.join()
    return result

def thread_per_cell(a, b):
    rows_a, cols_a, cols_b = len(a), len(a[0]), len(b[0])
    result = [[0] * cols_b for _ in range(rows_a)]

    def compute_cell(row, col):
        for k in range(cols_a):
            result[row][col] += a[row][k] * b[k][col]

    threads = [threading.Thread(target=compute_cell, args=(i, j))
               for i in range(rows_a) for j in range(cols_b)]
    for t in threads:
        t.start()
    for t in threads:
        t.join()
    return result

def input_matrix():
    rows = int(input("Enter number of rows: "))
    cols = int(input("Enter number of columns: "))
    print(f"Enter {rows}x{cols} matrix values:")
    matrix = []
    for i in range(rows):
        row = list(map(float, input(f"Row {i+1} (space-separated): ").split()))
        matrix.append(row)
    return matrix

def print_matrix(matrix):
    for row in matrix:
        print([round(val, 2) for val in row])


# Main
print("=== Matrix Multiplication ===\n")
print("Choose input method:")
print("1. Manual input")
print("2. Random matrices")
choice = input("Enter choice (1 or 2): ")

if choice == '1':
    print("\n--- Matrix A ---")
    a = input_matrix()
    print("\n--- Matrix B ---")
    b = input_matrix()
else:
    size = int(input("Enter matrix size (NxN): "))
    a = [[random.random() for _ in range(size)] for _ in range(size)]
    b = [[random.random() for _ in range(size)] for _ in range(size)]
    print(f"\nGenerated {size}x{size} random matrices")

if len(a[0]) != len(b):
    print("Error: Matrix dimensions incompatible for multiplication!")
else:
    print("\n--- Results ---")

    start = time.time()
    r1 = sequential_multiply(a, b)
    t1 = time.time() - start

    start = time.time()
    r2 = thread_per_row(a, b)
    t2 = time.time() - start

    # Only run thread per cell for small matrices
    if len(a) * len(b[0]) <= 100:
        start = time.time()
        r3 = thread_per_cell(a, b)
        t3 = time.time() - start

        print(f"\nSequential:      {t1:.4f}s")
        print(f"Thread per row:  {t2:.4f}s (Speedup: {t1 / t2 if t2 != 0 else 0:.2f}x)")
        print(f"Thread per cell: {t3:.4f}s (Speedup: {t1 / t3 if t3 != 0 else 0:.2f}x)")
    else:
        print(f"\nSequential:      {t1:.4f}s")
        print(f"Thread per row:  {t2:.4f}s (Speedup: {t1 / t2 if t2 != 0 else 0:.2f}x)")

    # Display result for small matrices
    if len(r1) <= 10 and len(r1[0]) <= 10:
        print("\nResult Matrix:")
        print_matrix(r1)  #can this code be short 
