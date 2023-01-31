import request from '@/utils/request'

// 查询个体列表
export function listPerson(query) {
  return request({
    url: '/standard/person/list',
    method: 'get',
    params: query
  })
}

// 查询个体详细
export function getPerson(id) {
  return request({
    url: '/standard/person/' + id,
    method: 'get'
  })
}

// 新增个体
export function addPerson(data) {
  return request({
    url: '/standard/person',
    method: 'post',
    data: data
  })
}

// 修改个体
export function updatePerson(data) {
  return request({
    url: '/standard/person',
    method: 'put',
    data: data
  })
}

// 删除个体
export function delPerson(id) {
  return request({
    url: '/standard/person/' + id,
    method: 'delete'
  })
}
