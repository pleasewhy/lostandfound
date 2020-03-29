package team.cfc.lostandfound.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class LostItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LostItemExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andPickerIdIsNull() {
            addCriterion("picker_id is null");
            return (Criteria) this;
        }

        public Criteria andPickerIdIsNotNull() {
            addCriterion("picker_id is not null");
            return (Criteria) this;
        }

        public Criteria andPickerIdEqualTo(Integer value) {
            addCriterion("picker_id =", value, "pickerId");
            return (Criteria) this;
        }

        public Criteria andPickerIdNotEqualTo(Integer value) {
            addCriterion("picker_id <>", value, "pickerId");
            return (Criteria) this;
        }

        public Criteria andPickerIdGreaterThan(Integer value) {
            addCriterion("picker_id >", value, "pickerId");
            return (Criteria) this;
        }

        public Criteria andPickerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("picker_id >=", value, "pickerId");
            return (Criteria) this;
        }

        public Criteria andPickerIdLessThan(Integer value) {
            addCriterion("picker_id <", value, "pickerId");
            return (Criteria) this;
        }

        public Criteria andPickerIdLessThanOrEqualTo(Integer value) {
            addCriterion("picker_id <=", value, "pickerId");
            return (Criteria) this;
        }

        public Criteria andPickerIdIn(List<Integer> values) {
            addCriterion("picker_id in", values, "pickerId");
            return (Criteria) this;
        }

        public Criteria andPickerIdNotIn(List<Integer> values) {
            addCriterion("picker_id not in", values, "pickerId");
            return (Criteria) this;
        }

        public Criteria andPickerIdBetween(Integer value1, Integer value2) {
            addCriterion("picker_id between", value1, value2, "pickerId");
            return (Criteria) this;
        }

        public Criteria andPickerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("picker_id not between", value1, value2, "pickerId");
            return (Criteria) this;
        }

        public Criteria andPickDateIsNull() {
            addCriterion("pick_date is null");
            return (Criteria) this;
        }

        public Criteria andPickDateIsNotNull() {
            addCriterion("pick_date is not null");
            return (Criteria) this;
        }

        public Criteria andPickDateEqualTo(Date value) {
            addCriterionForJDBCDate("pick_date =", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("pick_date <>", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateGreaterThan(Date value) {
            addCriterionForJDBCDate("pick_date >", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("pick_date >=", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateLessThan(Date value) {
            addCriterionForJDBCDate("pick_date <", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("pick_date <=", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateIn(List<Date> values) {
            addCriterionForJDBCDate("pick_date in", values, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("pick_date not in", values, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("pick_date between", value1, value2, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("pick_date not between", value1, value2, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickAddressIsNull() {
            addCriterion("pick_address is null");
            return (Criteria) this;
        }

        public Criteria andPickAddressIsNotNull() {
            addCriterion("pick_address is not null");
            return (Criteria) this;
        }

        public Criteria andPickAddressEqualTo(String value) {
            addCriterion("pick_address =", value, "pickAddress");
            return (Criteria) this;
        }

        public Criteria andPickAddressNotEqualTo(String value) {
            addCriterion("pick_address <>", value, "pickAddress");
            return (Criteria) this;
        }

        public Criteria andPickAddressGreaterThan(String value) {
            addCriterion("pick_address >", value, "pickAddress");
            return (Criteria) this;
        }

        public Criteria andPickAddressGreaterThanOrEqualTo(String value) {
            addCriterion("pick_address >=", value, "pickAddress");
            return (Criteria) this;
        }

        public Criteria andPickAddressLessThan(String value) {
            addCriterion("pick_address <", value, "pickAddress");
            return (Criteria) this;
        }

        public Criteria andPickAddressLessThanOrEqualTo(String value) {
            addCriterion("pick_address <=", value, "pickAddress");
            return (Criteria) this;
        }

        public Criteria andPickAddressLike(String value) {
            addCriterion("pick_address like", value, "pickAddress");
            return (Criteria) this;
        }

        public Criteria andPickAddressNotLike(String value) {
            addCriterion("pick_address not like", value, "pickAddress");
            return (Criteria) this;
        }

        public Criteria andPickAddressIn(List<String> values) {
            addCriterion("pick_address in", values, "pickAddress");
            return (Criteria) this;
        }

        public Criteria andPickAddressNotIn(List<String> values) {
            addCriterion("pick_address not in", values, "pickAddress");
            return (Criteria) this;
        }

        public Criteria andPickAddressBetween(String value1, String value2) {
            addCriterion("pick_address between", value1, value2, "pickAddress");
            return (Criteria) this;
        }

        public Criteria andPickAddressNotBetween(String value1, String value2) {
            addCriterion("pick_address not between", value1, value2, "pickAddress");
            return (Criteria) this;
        }

        public Criteria andImageIsNull() {
            addCriterion("image is null");
            return (Criteria) this;
        }

        public Criteria andImageIsNotNull() {
            addCriterion("image is not null");
            return (Criteria) this;
        }

        public Criteria andImageEqualTo(String value) {
            addCriterion("image =", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotEqualTo(String value) {
            addCriterion("image <>", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThan(String value) {
            addCriterion("image >", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThanOrEqualTo(String value) {
            addCriterion("image >=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThan(String value) {
            addCriterion("image <", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThanOrEqualTo(String value) {
            addCriterion("image <=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLike(String value) {
            addCriterion("image like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotLike(String value) {
            addCriterion("image not like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageIn(List<String> values) {
            addCriterion("image in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotIn(List<String> values) {
            addCriterion("image not in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageBetween(String value1, String value2) {
            addCriterion("image between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotBetween(String value1, String value2) {
            addCriterion("image not between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andOwnerNameIsNull() {
            addCriterion("owner_name is null");
            return (Criteria) this;
        }

        public Criteria andOwnerNameIsNotNull() {
            addCriterion("owner_name is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerNameEqualTo(String value) {
            addCriterion("owner_name =", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotEqualTo(String value) {
            addCriterion("owner_name <>", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameGreaterThan(String value) {
            addCriterion("owner_name >", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameGreaterThanOrEqualTo(String value) {
            addCriterion("owner_name >=", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameLessThan(String value) {
            addCriterion("owner_name <", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameLessThanOrEqualTo(String value) {
            addCriterion("owner_name <=", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameLike(String value) {
            addCriterion("owner_name like", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotLike(String value) {
            addCriterion("owner_name not like", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameIn(List<String> values) {
            addCriterion("owner_name in", values, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotIn(List<String> values) {
            addCriterion("owner_name not in", values, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameBetween(String value1, String value2) {
            addCriterion("owner_name between", value1, value2, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotBetween(String value1, String value2) {
            addCriterion("owner_name not between", value1, value2, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerTelephoneIsNull() {
            addCriterion("owner_telephone is null");
            return (Criteria) this;
        }

        public Criteria andOwnerTelephoneIsNotNull() {
            addCriterion("owner_telephone is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerTelephoneEqualTo(String value) {
            addCriterion("owner_telephone =", value, "ownerTelephone");
            return (Criteria) this;
        }

        public Criteria andOwnerTelephoneNotEqualTo(String value) {
            addCriterion("owner_telephone <>", value, "ownerTelephone");
            return (Criteria) this;
        }

        public Criteria andOwnerTelephoneGreaterThan(String value) {
            addCriterion("owner_telephone >", value, "ownerTelephone");
            return (Criteria) this;
        }

        public Criteria andOwnerTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("owner_telephone >=", value, "ownerTelephone");
            return (Criteria) this;
        }

        public Criteria andOwnerTelephoneLessThan(String value) {
            addCriterion("owner_telephone <", value, "ownerTelephone");
            return (Criteria) this;
        }

        public Criteria andOwnerTelephoneLessThanOrEqualTo(String value) {
            addCriterion("owner_telephone <=", value, "ownerTelephone");
            return (Criteria) this;
        }

        public Criteria andOwnerTelephoneLike(String value) {
            addCriterion("owner_telephone like", value, "ownerTelephone");
            return (Criteria) this;
        }

        public Criteria andOwnerTelephoneNotLike(String value) {
            addCriterion("owner_telephone not like", value, "ownerTelephone");
            return (Criteria) this;
        }

        public Criteria andOwnerTelephoneIn(List<String> values) {
            addCriterion("owner_telephone in", values, "ownerTelephone");
            return (Criteria) this;
        }

        public Criteria andOwnerTelephoneNotIn(List<String> values) {
            addCriterion("owner_telephone not in", values, "ownerTelephone");
            return (Criteria) this;
        }

        public Criteria andOwnerTelephoneBetween(String value1, String value2) {
            addCriterion("owner_telephone between", value1, value2, "ownerTelephone");
            return (Criteria) this;
        }

        public Criteria andOwnerTelephoneNotBetween(String value1, String value2) {
            addCriterion("owner_telephone not between", value1, value2, "ownerTelephone");
            return (Criteria) this;
        }

        public Criteria andReturnTimeIsNull() {
            addCriterion("return_time is null");
            return (Criteria) this;
        }

        public Criteria andReturnTimeIsNotNull() {
            addCriterion("return_time is not null");
            return (Criteria) this;
        }

        public Criteria andReturnTimeEqualTo(Date value) {
            addCriterion("return_time =", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeNotEqualTo(Date value) {
            addCriterion("return_time <>", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeGreaterThan(Date value) {
            addCriterion("return_time >", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("return_time >=", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeLessThan(Date value) {
            addCriterion("return_time <", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeLessThanOrEqualTo(Date value) {
            addCriterion("return_time <=", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeIn(List<Date> values) {
            addCriterion("return_time in", values, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeNotIn(List<Date> values) {
            addCriterion("return_time not in", values, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeBetween(Date value1, Date value2) {
            addCriterion("return_time between", value1, value2, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeNotBetween(Date value1, Date value2) {
            addCriterion("return_time not between", value1, value2, "returnTime");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}