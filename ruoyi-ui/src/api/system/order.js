import request from '@/utils/request'

// 查询订单列表
export function listOrders() {
  return request({
      url: '/system/order/list',
      method: 'get',
    })
}
// 通过用户名查询订单
export function queryOrderByUserName(userName) {
  return request({
    url: '/system/order/name/' + userName,
    method: 'get',
  })
}
