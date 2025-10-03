public class MinHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    private void heapifyUp(int index) {
        while (index > 0 && heap[(index - 1) / 2] > heap[index]) {
            swap((index - 1) / 2, index);
            index = (index - 1) / 2;
        }
    }

    private void heapifyDown(int index) {
        while (true) {
            int smallest = index;
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            if (left < size && heap[left] < heap[smallest])
                smallest = left;
            if (right < size && heap[right] < heap[smallest])
                smallest = right;

            if (smallest != index) {
                swap(smallest, index);
                index = smallest;
            } else {
                break;
            }
        }
    }

    public void buildHeap(int[] arr) {
        if (arr.length > capacity) {
            System.out.println("Array size exceeds capacity");
            return;
        }
        System.arraycopy(arr, 0, heap, 0, arr.length);
        size = arr.length;
        for (int i = (size - 2) / 2; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    public void insert(int key) {
        if (size == capacity) {
            System.out.println("Heap is full");
            return;
        }
        heap[size] = key;
        size++;
        heapifyUp(size - 1);
    }

    public int extractMin() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return -1;
        }
        int minVal = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return minVal;
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    // Тестовая программа
    public static void main(String[] args) {
        int[] arr = {10, 4, 5, 30, 3, 1};
        MinHeap heap = new MinHeap(20);
        heap.buildHeap(arr);

        System.out.println("Heap after build:");
        heap.printHeap();

        heap.insert(0);
        System.out.println("After inserting 0:");
        heap.printHeap();

        System.out.println("Extract min: " + heap.extractMin());
        System.out.println("Heap after extraction:");
        heap.printHeap();
    }
}