import request from '@/utils/request'

// 查询人口居住轨迹列表
export function listTrack(query) {
  return request({
    url: '/standard/track/list',
    method: 'get',
    params: query
  })
}

// 查询人口居住轨迹详细
export function getTrack(trackId) {
  return request({
    url: '/standard/track/' + trackId,
    method: 'get'
  })
}

// 新增人口居住轨迹
export function addTrack(data) {
  return request({
    url: '/standard/track',
    method: 'post',
    data: data
  })
}

// 修改人口居住轨迹
export function updateTrack(data) {
  return request({
    url: '/standard/track',
    method: 'put',
    data: data
  })
}

// 删除人口居住轨迹
export function delTrack(trackId) {
  return request({
    url: '/standard/track/' + trackId,
    method: 'delete'
  })
}
