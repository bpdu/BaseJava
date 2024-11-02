/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int index = 0;

    void clear() {
        for (int i = 0; i < index; i++) {
            storage[i] = null;
            index = 0;
        }
    }

    void save(Resume r) {
        if (index < 10000) {
            storage[index] = r;
            index++;
        } else {
            System.out.println("Storage is full");
        }
    }

    Resume get(String uuid) {
        Resume res = null;
        int i = 0;
        while (i < index && res == null) {
            res = storage[i].toString().equals(uuid) ? storage[i] : null;
            i++;
        }
        return res;
    }

    void delete(String uuid) {
        int notNullIndex = index;
        int i = 0;
        do {
            Resume res = storage[i];
            if (res.toString().equals(uuid) || res == null ) {
                storage[i] = storage[i + 1];
                notNullIndex--;
            }
            i++;
        } while (i < index);
        index = notNullIndex;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] arr = new Resume[index];
        System.arraycopy(storage, 0, arr, 0, index);
        return arr;
    }

    int size() {
        return index;
    }
}
