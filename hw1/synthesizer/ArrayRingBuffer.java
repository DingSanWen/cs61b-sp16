// Make sure to make this class a part of the synthesizer package
package synthesizer;
import java.util.Iterator;

//Make sure to make this class and all of its methods public
//Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // Enqueue the item. Don't forget to increase fillCount and update last.
        if (fillCount < capacity) {
            rb[last] = x;
            fillCount++;
            last = (last + 1) % this.capacity;
        } else {
            throw new RuntimeException("Ring Buffer Overflow");
        }

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // Dequeue the first item. Don't forget to decrease fillCount and update
        if (fillCount != 0) {
            T res = rb[first];
            rb[first] = null;
            fillCount--;
            first = (first + 1) % this.capacity;
            return res;
        } else {
            throw new RuntimeException("Ring Buffer Underflow");
        }

    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // Return the first item. None of your instance variables should change.
        return rb[first];
    }

    // When you get to part 5, implement the needed code to support iteration.
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    public class MyIterator implements Iterator<T> {
        private int index;
        private int time;
        public MyIterator() {
            index = first - 1;
            time = 0;
        }
        public boolean hasNext() {
            return time == fillCount;
        }

        public T next() {
            index++;
            time++;
            return rb[index];

        }
    }
}
