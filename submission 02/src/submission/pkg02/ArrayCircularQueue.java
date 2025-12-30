package submission.pkg02;

class ArrayCircularQueue {
    int[] q;
    int front = -1, rear = -1, size;

    ArrayCircularQueue(int size) {
        this.size = size;
        q = new int[size];
    }

    boolean isFull() {
        return (rear + 1) % size == front;
    }

    boolean isEmpty() {
        return front == -1;
    }

    void enqueue(int x) {
        if (isFull()) return;

        if (front == -1)
            front = 0;

        rear = (rear + 1) % size;
        q[rear] = x;
    }

    int dequeue() {
        if (isEmpty()) return -1;

        int val = q[front];

        if (front == rear)
            front = rear = -1;
        else
            front = (front + 1) % size;

        return val;
    }
}
