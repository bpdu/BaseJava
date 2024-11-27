package ru.bpdu.basejava.storage;

import ru.bpdu.basejava.model.Resume;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (size >= STORAGE_LIMIT - 1) {
            System.out.printf("Error saving resume with uuid '%s': Array is full\n", r.getUuid());
        } else if (size > 0 && index > 0) {
            System.out.printf("Error saving resume with uuid '%s': Resume already exists\n", r.getUuid());
        } else {
            storage[size++] = r;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.printf("Error getting resume with uuid '%s': Resume not found\n", uuid);
            return null;
        } else {
            return storage[index];
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.printf("Error deleting resume with uuid '%s': Resume not found\n", uuid);
        } else {
            storage[index] = storage[--size];
            storage[size] = null;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public Resume update(String uuid, Resume r) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.printf("Error updating resume with uuid '%s': Resume not found\n", uuid);
            return null;
        } else {
            storage[index] = r;
            return storage[index];
        }
    }

    protected int getIndex(String uuid) {
        return IntStream.range(0, size).filter(i -> storage[i].getUuid().equals(uuid)).findFirst().orElse(-1);
    }
}
