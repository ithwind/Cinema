import request from '@/utils/request'

// 查询轮播图列表
export function listSwipers() {
    return request({
        url: '/system/swiper/list',
        method: 'get',
      })
}
// 通过id查询轮播图信息
export function getSwiper(swiperId) {
  return request({
    url: '/system/swiper/' + swiperId,
    method: 'get'
  })
}
// 修改轮播图信息
export function updateSwiper(data) {
  return request({
    url: '/system/swiper',
    method: 'put',
    data: data
  })
}
// 删除轮播图
export function delSwiper(swiperIds) {
  return request({
    url: '/system/swiper/' + swiperIds,
    method: 'delete'
  })
}
// 新增轮播图
export function addSwiper(data) {
  return request({
    url: '/system/swiper',
    method: 'post',
    data: data
  })
}
// 通过电影名字查询信息
export function querySwiperByFilmName(filmName) {
  return request({
    url: '/system/swiper/name/' + filmName,
    method: 'get',
  })
}