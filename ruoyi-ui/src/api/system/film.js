import request from '@/utils/request'

// 查询电影列表
export function listFilm() {
    return request({
        url: '/system/film/list',
        method: 'get',
      })
}

// 通过id查询电影
export function getFilm(filmId) {
    return request({
      url: '/system/film/' + filmId,
      method: 'get'
    })
}

// 修改电影
export function updateFilm(data) {
  return request({
    url: '/system/film',
    method: 'put',
    data: data
  })
}
// 新增电影
export function addFilm(data) {
  return request({
    url: '/system/film',
    method: 'post',
    data: data
  })
}
// 删除电影
export function delFilm(filmIds) {
  return request({
    url: '/system/film/' + filmIds,
    method: 'delete'
  })
}
// 通过电影名称查询电影列表
export function queryFilmsByFilmName(filmName) {
  return request({
    url: '/system/film/name/' + filmName,
    method: 'get',
  })
}
