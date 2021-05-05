import {request} from './index.js';

const orderDomin = "http://k4d104.p.ssafy.io/:8084/order/";

export const order = { // crud
    // order from seller

    // getOrdersByProductIdSortedByDayofWeek
    fetchProductDayOfWeek(productId) {
        return request(orderDomin, 'get', `prodcut/${productId}/dayofweek`);
    },
    //getOrdersBySellerId
    fetchSellerid(sellerid) {
        return request(orderDomin, 'get', `sellerid/${sellerid}`);
    },
    // getOrdersBySellerIdBystatus
    fetchSelleridStatus(sellerid, statusnum) {
        return request(orderDomin, 'get', `sellerid/${sellerid}/status/${statusnum}`);
    },
    //getOrdersBySellerIdWithUnchecked
    fetchSelleridUnchecked(sellerid) {
        return request(orderDomin, 'get', `sellerid/${sellerid}/unchecked`);
    },
    //getOrdersBySellerIdWithUnchecked
    fetchSelleridUnchecked(sellerid) {
        return request(orderDomin, 'get', `sellerid/${sellerid}/unchecked`);
    },
    //getOrdersBySellerIdByday
    fetchSellerMonth(sellerid, month) {
        return request(orderDomin, 'get', `sellerid/${sellerid}/${month}`);
    },

    // order from user

    //getOrdersByUserIdBystatus 
    fetchUserStatus(userid, statusnum) {
        return request(orderDomin, 'get', `userid/${userid}/status/${statusnum}`);
    },

    //getOrders
    fetchUserAll(userId) {
        return request(orderDomin, 'get', `userid/${userId}/all`);
    },

    //getOrdersByUserId
    fetchUserOrders(userId, page, size) {
        return request(orderDomin, 'get', `userid/${userId}/${page}/${size}`);
    },

    //todayHot
    fetchRecommendTodayHot() {
        return request(orderDomin, 'get', `recommend/todayhot`);
    },

    //steadySeller
    fetchRecommendSteadySeller() {
        return request(orderDomin, 'get', `recommend/steadyseller`);
    },

    //hotProduct
    fetchRecommendHotProduct() {
        return request(orderDomin, 'get', `recommend/hotproduct`);
    },

    //updateOrders
    updateOrder(orderDetailEntity) {
        return request(orderDomin, 'put', ``, {orderDetailEntity});
    },

    //AddOrders
    createOrder(orderDetailEntity) {
        return request(orderDomin, 'post', ``, {orderDetailEntity});
    },
};

