/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
            size = 0;
        }
    }

    void save(Resume r) {
        if (size < 10000) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Storage is full");
        }
    }

    Resume get(String uuid) {
        Resume res = null;
        int i = 0;
        while (i < size && res == null) {
            res = storage[i].toString().equals(uuid) ? storage[i] : null;
            i++;
        }
        return res;
    }

    void delete(String uuid) {
        int notNullIndex = size;
        int i = 0;
        do {
            Resume res = storage[i];
            if (res.toString().equals(uuid) || res == null) {
                storage[i] = storage[i + 1];
                notNullIndex--;
            }
            i++;
        } while (i < size);
        size = notNullIndex;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    int size() {
        return size;
    }
}
