package team.cfc.lostandfound.model;

import java.util.ArrayList;
import java.util.Date;
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

        public Criteria andPickTimeIsNull() {
            addCriterion("pick_time is null");
            return (Criteria) this;
        }

        public Criteria andPickTimeIsNotNull() {
            addCriterion("pick_time is not null");
            return (Criteria) this;
        }

        public Criteria andPickTimeEqualTo(String value) {
            addCriterion("pick_time =", value, "pickTime");
            return (Criteria) this;
        }

        public Criteria andPickTimeNotEqualTo(String value) {
            addCriterion("pick_time <>", value, "pickTime");
            return (Criteria) this;
        }

        public Criteria andPickTimeGreaterThan(String value) {
            addCriterion("pick_time >", value, "pickTime");
            return (Criteria) this;
        }

        public Criteria andPickTimeGreaterThanOrEqualTo(String value) {
            addCriterion("pick_time >=", value, "pickTime");
            return (Criteria) this;
        }

        public Criteria andPickTimeLessThan(String value) {
            addCriterion("pick_time <", value, "pickTime");
            return (Criteria) this;
        }

        public Criteria andPickTimeLessThanOrEqualTo(String value) {
            addCriterion("pick_time <=", value, "pickTime");
            return (Criteria) this;
        }

        public Criteria andPickTimeLike(String value) {
            addCriterion("pick_time like", value, "pickTime");
            return (Criteria) this;
        }

        public Criteria andPickTimeNotLike(String value) {
            addCriterion("pick_time not like", value, "pickTime");
            return (Criteria) this;
        }

        public Criteria andPickTimeIn(List<String> values) {
            addCriterion("pick_time in", values, "pickTime");
            return (Criteria) this;
        }

        public Criteria andPickTimeNotIn(List<String> values) {
            addCriterion("pick_time not in", values, "pickTime");
            return (Criteria) this;
        }

        public Criteria andPickTimeBetween(String value1, String value2) {
            addCriterion("pick_time between", value1, value2, "pickTime");
            return (Criteria) this;
        }

        public Criteria andPickTimeNotBetween(String value1, String value2) {
            addCriterion("pick_time not between", value1, value2, "pickTime");
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

        public Criteria andImageUrlIsNull() {
            addCriterion("image_url is null");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNotNull() {
            addCriterion("image_url is not null");
            return (Criteria) this;
        }

        public Criteria andImageUrlEqualTo(String value) {
            addCriterion("image_url =", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotEqualTo(String value) {
            addCriterion("image_url <>", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThan(String value) {
            addCriterion("image_url >", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("image_url >=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThan(String value) {
            addCriterion("image_url <", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThanOrEqualTo(String value) {
            addCriterion("image_url <=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLike(String value) {
            addCriterion("image_url like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotLike(String value) {
            addCriterion("image_url not like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlIn(List<String> values) {
            addCriterion("image_url in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotIn(List<String> values) {
            addCriterion("image_url not in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlBetween(String value1, String value2) {
            addCriterion("image_url between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotBetween(String value1, String value2) {
            addCriterion("image_url not between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andLabelIsNull() {
            addCriterion("`label` is null");
            return (Criteria) this;
        }

        public Criteria andLabelIsNotNull() {
            addCriterion("`label` is not null");
            return (Criteria) this;
        }

        public Criteria andLabelEqualTo(String value) {
            addCriterion("`label` =", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotEqualTo(String value) {
            addCriterion("`label` <>", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThan(String value) {
            addCriterion("`label` >", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThanOrEqualTo(String value) {
            addCriterion("`label` >=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThan(String value) {
            addCriterion("`label` <", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThanOrEqualTo(String value) {
            addCriterion("`label` <=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLike(String value) {
            addCriterion("`label` like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotLike(String value) {
            addCriterion("`label` not like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelIn(List<String> values) {
            addCriterion("`label` in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotIn(List<String> values) {
            addCriterion("`label` not in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelBetween(String value1, String value2) {
            addCriterion("`label` between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotBetween(String value1, String value2) {
            addCriterion("`label` not between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andRegionIdIsNull() {
            addCriterion("region_id is null");
            return (Criteria) this;
        }

        public Criteria andRegionIdIsNotNull() {
            addCriterion("region_id is not null");
            return (Criteria) this;
        }

        public Criteria andRegionIdEqualTo(Integer value) {
            addCriterion("region_id =", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotEqualTo(Integer value) {
            addCriterion("region_id <>", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdGreaterThan(Integer value) {
            addCriterion("region_id >", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("region_id >=", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLessThan(Integer value) {
            addCriterion("region_id <", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLessThanOrEqualTo(Integer value) {
            addCriterion("region_id <=", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdIn(List<Integer> values) {
            addCriterion("region_id in", values, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotIn(List<Integer> values) {
            addCriterion("region_id not in", values, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdBetween(Integer value1, Integer value2) {
            addCriterion("region_id between", value1, value2, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("region_id not between", value1, value2, "regionId");
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

        public Criteria andSubmitTimeIsNull() {
            addCriterion("submit_time is null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNotNull() {
            addCriterion("submit_time is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeEqualTo(Date value) {
            addCriterion("submit_time =", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotEqualTo(Date value) {
            addCriterion("submit_time <>", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThan(Date value) {
            addCriterion("submit_time >", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("submit_time >=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThan(Date value) {
            addCriterion("submit_time <", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThanOrEqualTo(Date value) {
            addCriterion("submit_time <=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIn(List<Date> values) {
            addCriterion("submit_time in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotIn(List<Date> values) {
            addCriterion("submit_time not in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeBetween(Date value1, Date value2) {
            addCriterion("submit_time between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotBetween(Date value1, Date value2) {
            addCriterion("submit_time not between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andRecoverMethodIsNull() {
            addCriterion("recover_method is null");
            return (Criteria) this;
        }

        public Criteria andRecoverMethodIsNotNull() {
            addCriterion("recover_method is not null");
            return (Criteria) this;
        }

        public Criteria andRecoverMethodEqualTo(Integer value) {
            addCriterion("recover_method =", value, "recoverMethod");
            return (Criteria) this;
        }

        public Criteria andRecoverMethodNotEqualTo(Integer value) {
            addCriterion("recover_method <>", value, "recoverMethod");
            return (Criteria) this;
        }

        public Criteria andRecoverMethodGreaterThan(Integer value) {
            addCriterion("recover_method >", value, "recoverMethod");
            return (Criteria) this;
        }

        public Criteria andRecoverMethodGreaterThanOrEqualTo(Integer value) {
            addCriterion("recover_method >=", value, "recoverMethod");
            return (Criteria) this;
        }

        public Criteria andRecoverMethodLessThan(Integer value) {
            addCriterion("recover_method <", value, "recoverMethod");
            return (Criteria) this;
        }

        public Criteria andRecoverMethodLessThanOrEqualTo(Integer value) {
            addCriterion("recover_method <=", value, "recoverMethod");
            return (Criteria) this;
        }

        public Criteria andRecoverMethodIn(List<Integer> values) {
            addCriterion("recover_method in", values, "recoverMethod");
            return (Criteria) this;
        }

        public Criteria andRecoverMethodNotIn(List<Integer> values) {
            addCriterion("recover_method not in", values, "recoverMethod");
            return (Criteria) this;
        }

        public Criteria andRecoverMethodBetween(Integer value1, Integer value2) {
            addCriterion("recover_method between", value1, value2, "recoverMethod");
            return (Criteria) this;
        }

        public Criteria andRecoverMethodNotBetween(Integer value1, Integer value2) {
            addCriterion("recover_method not between", value1, value2, "recoverMethod");
            return (Criteria) this;
        }

        public Criteria andRecoverDetailsIsNull() {
            addCriterion("recover_details is null");
            return (Criteria) this;
        }

        public Criteria andRecoverDetailsIsNotNull() {
            addCriterion("recover_details is not null");
            return (Criteria) this;
        }

        public Criteria andRecoverDetailsEqualTo(String value) {
            addCriterion("recover_details =", value, "recoverDetails");
            return (Criteria) this;
        }

        public Criteria andRecoverDetailsNotEqualTo(String value) {
            addCriterion("recover_details <>", value, "recoverDetails");
            return (Criteria) this;
        }

        public Criteria andRecoverDetailsGreaterThan(String value) {
            addCriterion("recover_details >", value, "recoverDetails");
            return (Criteria) this;
        }

        public Criteria andRecoverDetailsGreaterThanOrEqualTo(String value) {
            addCriterion("recover_details >=", value, "recoverDetails");
            return (Criteria) this;
        }

        public Criteria andRecoverDetailsLessThan(String value) {
            addCriterion("recover_details <", value, "recoverDetails");
            return (Criteria) this;
        }

        public Criteria andRecoverDetailsLessThanOrEqualTo(String value) {
            addCriterion("recover_details <=", value, "recoverDetails");
            return (Criteria) this;
        }

        public Criteria andRecoverDetailsLike(String value) {
            addCriterion("recover_details like", value, "recoverDetails");
            return (Criteria) this;
        }

        public Criteria andRecoverDetailsNotLike(String value) {
            addCriterion("recover_details not like", value, "recoverDetails");
            return (Criteria) this;
        }

        public Criteria andRecoverDetailsIn(List<String> values) {
            addCriterion("recover_details in", values, "recoverDetails");
            return (Criteria) this;
        }

        public Criteria andRecoverDetailsNotIn(List<String> values) {
            addCriterion("recover_details not in", values, "recoverDetails");
            return (Criteria) this;
        }

        public Criteria andRecoverDetailsBetween(String value1, String value2) {
            addCriterion("recover_details between", value1, value2, "recoverDetails");
            return (Criteria) this;
        }

        public Criteria andRecoverDetailsNotBetween(String value1, String value2) {
            addCriterion("recover_details not between", value1, value2, "recoverDetails");
            return (Criteria) this;
        }

        public Criteria andReceiveLocationIsNull() {
            addCriterion("receive_location is null");
            return (Criteria) this;
        }

        public Criteria andReceiveLocationIsNotNull() {
            addCriterion("receive_location is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveLocationEqualTo(String value) {
            addCriterion("receive_location =", value, "receiveLocation");
            return (Criteria) this;
        }

        public Criteria andReceiveLocationNotEqualTo(String value) {
            addCriterion("receive_location <>", value, "receiveLocation");
            return (Criteria) this;
        }

        public Criteria andReceiveLocationGreaterThan(String value) {
            addCriterion("receive_location >", value, "receiveLocation");
            return (Criteria) this;
        }

        public Criteria andReceiveLocationGreaterThanOrEqualTo(String value) {
            addCriterion("receive_location >=", value, "receiveLocation");
            return (Criteria) this;
        }

        public Criteria andReceiveLocationLessThan(String value) {
            addCriterion("receive_location <", value, "receiveLocation");
            return (Criteria) this;
        }

        public Criteria andReceiveLocationLessThanOrEqualTo(String value) {
            addCriterion("receive_location <=", value, "receiveLocation");
            return (Criteria) this;
        }

        public Criteria andReceiveLocationLike(String value) {
            addCriterion("receive_location like", value, "receiveLocation");
            return (Criteria) this;
        }

        public Criteria andReceiveLocationNotLike(String value) {
            addCriterion("receive_location not like", value, "receiveLocation");
            return (Criteria) this;
        }

        public Criteria andReceiveLocationIn(List<String> values) {
            addCriterion("receive_location in", values, "receiveLocation");
            return (Criteria) this;
        }

        public Criteria andReceiveLocationNotIn(List<String> values) {
            addCriterion("receive_location not in", values, "receiveLocation");
            return (Criteria) this;
        }

        public Criteria andReceiveLocationBetween(String value1, String value2) {
            addCriterion("receive_location between", value1, value2, "receiveLocation");
            return (Criteria) this;
        }

        public Criteria andReceiveLocationNotBetween(String value1, String value2) {
            addCriterion("receive_location not between", value1, value2, "receiveLocation");
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