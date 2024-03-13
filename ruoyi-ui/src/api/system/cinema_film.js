import request from '@/utils/request'

// 查询电影与影院关联列表
export function listCinemaFilms() {
    return request({
        url: '/system/cinema-film/list',
        method: 'get',
      })
}
// 通过id查询关联信息
export function getCinemaFilm(cinemaFilmId) {
  return request({
    url: '/system/cinema-film/' + cinemaFilmId,
    method: 'get'
  })
}
// 修改关联信息
export function updateCinemaFilm(data) {
  console.log(data);
  return request({
    url: '/system/cinema-film',
    method: 'put',
    data: data
  })
}
// 删除关联信息
export function delCinemaFilm(cinemaFilmIds) {
  return request({
    url: '/system/cinema-film/' + cinemaFilmIds,
    method: 'delete'
  })
}

// 新增关联信息
export function addCinemaFilm(data) {
  return request({
    url: '/system/cinema-film',
    method: 'post',
    data: data
  })
}
// 通过id查询信息
export function queryCinemaFilmsByfilmId(filmId) {
  return request({
    url: '/system/cinema-film/filmId/' + filmId,
    method: 'get',
  })
}