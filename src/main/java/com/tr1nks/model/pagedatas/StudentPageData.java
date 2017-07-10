package com.tr1nks.model.pagedatas;

import com.tr1nks.model.entities.FacultyEntity;
import com.tr1nks.model.entities.GroupEntity;
import com.tr1nks.model.entities.StudentEntity;

import java.util.List;

/**
 * данные для страницы student
 */
public class StudentPageData extends PageData {
    /**
     * студенты
     */
    private List<StudentEntity> students;
    /**
     * выбранные студенты
     */
    private List<Integer> selectedStudents;
    /**
     * варианты фильтров групп
     */
    private List<GroupEntity> variantGroups;
    /**
     * отфильтрованные группы
     */
    private List<Integer> filterGroups;
    /**
     * варианты фильтров годов
     */
    private List<Integer> variantYears;
    /**
     * отфильтрованные года
     */
    private List<Integer> filterYears;
    /**
     * варианты фильтров факультетов
     */
    private List<FacultyEntity> variantFacults;
    /**
     * отфильтрованные факультеты
     */
    private List<Integer> filterFacults;
    /**
     * флаг о наличии ошибки
     */
    private boolean errFlg = false;

    /**
     * конструктор
     */
    public StudentPageData() {
    }

    /**
     * конструктор
     *
     * @param students студенты
     */
    public StudentPageData(List<StudentEntity> students) {
        this.students = students;
    }

    /**
     * получить студентов
     *
     * @return студенты
     */
    public List<StudentEntity> getStudents() {
        return students;
    }

    /**
     * установить студентов
     *
     * @param students студенты
     */
    public void setStudents(List<StudentEntity> students) {
        this.students = students;
    }

    /**
     * получить отфильтрованные группы
     *
     * @return отфильтрованные группы
     */
    public List<Integer> getFilterGroups() {
        return filterGroups;
    }

    /**
     * установить отфильтрованные группы
     *
     * @param filterGroups отфильтрованные группы
     */
    public void setFilterGroups(List<Integer> filterGroups) {
        this.filterGroups = filterGroups;
    }

    /**
     * получить отфильтрованные года
     *
     * @return отфильтрованные года
     */
    public List<Integer> getFilterYears() {
        return filterYears;
    }

    /**
     * установить отфильтрованные года
     *
     * @param filterYears отфильтрованные года
     */
    public void setFilterYears(List<Integer> filterYears) {
        this.filterYears = filterYears;
    }

    /**
     * получить отфильтрованные факультеты
     *
     * @return отфильтрованные факультеты
     */
    public List<Integer> getFilterFacults() {
        return filterFacults;
    }

    /**
     * установить отфильтрованные факультеты
     *
     * @param filterFacults отфильтрованные факультеты
     */
    public void setFilterFacults(List<Integer> filterFacults) {
        this.filterFacults = filterFacults;
    }

    /**
     * получить варианты фильтров групп
     *
     * @return варианты фильтров групп
     */
    public List<GroupEntity> getVariantGroups() {
        return variantGroups;
    }

    /**
     * установить установить
     *
     * @param variantGroups установить
     */
    public void setVariantGroups(List<GroupEntity> variantGroups) {
        this.variantGroups = variantGroups;
    }

    /**
     * получить варианты фильтров годов
     *
     * @return варианты фильтров годов
     */
    public List<Integer> getVariantYears() {
        return variantYears;
    }

    /**
     * установить варианты фильтров годов
     *
     * @param variantYears варианты фильтров годов
     */
    public void setVariantYears(List<Integer> variantYears) {
        this.variantYears = variantYears;
    }

    /**
     * получить варианты фильтров факультетов
     *
     * @return варианты фильтров факультетов
     */
    public List<FacultyEntity> getVariantFacults() {
        return variantFacults;
    }

    /**
     * установить варианты фильтров факультетов
     *
     * @param variantFacults варианты фильтров факультетов
     */
    public void setVariantFacults(List<FacultyEntity> variantFacults) {
        this.variantFacults = variantFacults;
    }

    /**
     * получить выбранные студенты
     *
     * @return выбранные студенты
     */
    public List<Integer> getSelectedStudents() {
        return selectedStudents;
    }

    /**
     * установить выбранные студенты
     *
     * @param selectedStudents выбранные студенты
     */
    public void setSelectedStudents(List<Integer> selectedStudents) {
        this.selectedStudents = selectedStudents;
    }

    /**
     * установить флаг о наличии ошибки
     *
     * @param errFlg флаг о наличии ошибки
     */
    public void setErrFlg(boolean errFlg) {
        this.errFlg = errFlg;
    }

    /**
     * получить флаг о наличии ошибки
     *
     * @return флаг о наличии ошибки
     */
    public boolean isErrFlg() {
        return errFlg;
    }
}
