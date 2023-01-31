import request from '@/utils/request'

// 查询单位类别关系列表
export function listDetails(query) {
  return request({
    url: '/standard/details/list',
    method: 'get',
    params: query
  })
}

// 查询单位类别关系详细
export function getDetails(detailId) {
  return request({
    url: '/standard/details/' + detailId,
    method: 'get'
  })
}

// 新增单位类别关系
export function addDetails(data) {
  return request({
    url: '/standard/details',
    method: 'post',
    data: data
  })
}

// 修改单位类别关系
export function updateDetails(data) {
  return request({
    url: '/standard/details',
    method: 'put',
    data: data
  })
}

// 删除单位类别关系
export function delDetails(detailId) {
  return request({
    url: '/standard/details/' + detailId,
    method: 'delete'
  })
}
