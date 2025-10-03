#include <iostream>
#include <vector>
#include <stdexcept>

class MinHeap {
private:
    std::vector<int> heap;

    void heapifyUp(int index) {
        while (index > 0 && heap[(index - 1) / 2] > heap[index]) {
            std::swap(heap[(index - 1) / 2], heap[index]);
            index = (index - 1) / 2;
        }
    }

    void heapifyDown(int index) {
        int size = heap.size();
        while (true) {
            int smallest = index;
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            if (left < size && heap[left] < heap[smallest])
                smallest = left;
            if (right < size && heap[right] < heap[smallest])
                smallest = right;

            if (smallest != index) {
                std::swap(heap[smallest], heap[index]);
                index = smallest;
            } else {
                break;
            }
        }
    }

public:
    MinHeap() {}

    void buildHeap(const std::vector<int>& arr) {
        heap = arr;
        for (int i = (heap.size() - 2) / 2; i >= 0; --i) {
            heapifyDown(i);
        }
    }

    void insert(int key) {
        heap.push_back(key);
        heapifyUp(heap.size() - 1);
    }

    int extractMin() {
        if (heap.empty())
            throw std::out_of_range("Heap is empty");
        int minVal = heap[0];
        heap[0] = heap.back();
        heap.pop_back();
        if (!heap.empty())
            heapifyDown(0);
        return minVal;
    }

    void printHeap() {
        for (int val : heap) {
            std::cout << val << " ";
        }
        std::cout << std::endl;
    }
};

int main() {
    std::vector<int> arr = {10, 4, 5, 30, 3, 1};
    MinHeap heap;
    heap.buildHeap(arr);

    std::cout << "Heap after build: ";
    heap.printHeap();

    heap.insert(0);
    std::cout << "After inserting 0: ";
    heap.printHeap();

    std::cout << "Extract min: " << heap.extractMin() << std::endl;
    std::cout << "Heap after extraction: ";
    heap.printHeap();

    return 0;
}