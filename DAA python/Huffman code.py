import heapq

# A class to store nodes of Huffman Tree
class Node:
    def __init__(self, char, freq):
        self.char = char
        self.freq = freq
        self.left = None
        self.right = None

    # Define comparison for priority queue
    def __lt__(self, other):
        return self.freq < other.freq

# Function to print Huffman Codes
def print_codes(node, code=""):
    if node is None:
        return
    if node.char is not None:
        print(f"{node.char}: {code}")
    print_codes(node.left, code + "0")
    print_codes(node.right, code + "1")

# Huffman Encoding Function
def huffman_encoding(char_freq):
    # Step 1: Create a priority queue (min-heap)
    heap = [Node(char, freq) for char, freq in char_freq.items()]
    heapq.heapify(heap)

    # Step 2: Build Huffman Tree
    while len(heap) > 1:
        left = heapq.heappop(heap)
        right = heapq.heappop(heap)
        new_node = Node(None, left.freq + right.freq)
        new_node.left = left
        new_node.right = right
        heapq.heappush(heap, new_node)

    # Step 3: Print Huffman Codes
    print("Character | Huffman Code")
    print("------------------------")
    print_codes(heap[0])

# Main Program
if __name__ == "__main__":
    text = input("Enter text to encode: ")

    # Calculate frequency of each character
    freq = {}
    for ch in text:
        freq[ch] = freq.get(ch, 0) + 1

    print("\nCharacter Frequencies:")
    for char, f in freq.items():
        print(f"{char}: {f}")

    print("\nHuffman Encoding:")
    huffman_encoding(freq)
