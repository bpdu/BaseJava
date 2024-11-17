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
        int index = size;
        if (index >= STORAGE_LIMIT - 1) {
            System.out.printf("Error saving resume with uuid '%s': Array is full\n", r.getUuid());
        } else if ((index > 0) && (getIndex(r.getUuid()) > 0)) {
            System.out.printf("Error saving resume with uuid '%s': Resume already exists\n", r.getUuid());
        } else {
            storage[index++] = r;
        }
        size = index;
    }

    public Resume get(String uuid) {
        int index = size;
        Resume resume = Arrays.stream(storage).limit(index).filter(r -> r.getUuid().equals(uuid)).findFirst().orElse(null);
        if (resume == null) {
            System.out.printf("Error getting resume with uuid '%s': Resume not found\n", uuid);
        }
        return resume;
    }

    public void delete(String uuid) {
        int index = size;
        if (Arrays.stream(storage).limit(index).noneMatch(resume -> resume.getUuid().equals(uuid))) {
            System.out.printf("Error deleting resume with uuid '%s': Resume not found\n", uuid);
        } else {
            for (int i = 0; i < size; i++) {
                storage[i] = storage[i].getUuid().equals(uuid) ? storage[index-- - 1] : storage[i];
            }
        }
        size = index;
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
        int index = size;
        if (Arrays.stream(storage).limit(index).anyMatch(resume -> resume.getUuid().equals(uuid))) {
            return new Resume(r.getUuid());
        } else {
            System.out.printf("Error updating resume with uuid '%s': Resume not found\n", uuid);
            return null;
        }
    }

    protected int getIndex(String uuid) {
        int index = size;
        return IntStream.range(0, index - 1)
                .filter(i -> storage[i].getUuid().equals(uuid))
                .findFirst()
                .orElse(-1);
    }
}
