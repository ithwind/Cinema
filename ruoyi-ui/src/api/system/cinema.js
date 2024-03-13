import request from '@/utils/request'

// 查询电影院列表
export function listCinema() {
    return request({
        url: '/system/cinema/list',
        method: 'get',
      })
}

// 通过id查询影院
export function getCinema(cinemaId) {
    return request({
      url: '/system/cinema/' + cinemaId,
      method: 'get'
    })
}
// 修改影院信息
export function updateCinema(data) {
    console.log(data);
    return request({
      url: '/system/cinema',
      method: 'put',
      data: data
    })
}
// 新增影院
export function addCinema(data) {
  return request({
    url: '/system/cinema',
    method: 'post',
    data: data
  })
}
// 删除影院
export function delCinema(cinemaIds) {
  return request({
    url: '/system/cinema/' + cinemaIds,
    method: 'delete'
  })
}
// 通过电影名称查询电影列表
export function queryCinemasByCinemaName(cinemaName) {
  return request({
    url: '/system/cinema/name/' + cinemaName,
    method: 'get',
  })
}
