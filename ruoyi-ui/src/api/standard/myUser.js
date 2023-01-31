import request from '@/utils/request'

// 查询用户信息列表
export function listUser(query) {
  return request({
    url: '/standard/myUser/list',
    method: 'get',
    params: query
  })
}

// 查询用户信息详细
export function getUser(userId) {
  return request({
    url: '/standard/myUser/' + userId,
    method: 'get'
  })
}

// 新增用户信息
export function addUser(data) {
  return request({
    url: '/standard/myUser',
    method: 'post',
    data: data
  })
}

// 修改用户信息
export function updateUser(data) {
  return request({
    url: '/standard/myUser',
    method: 'put',
    data: data
  })
}

// 删除用户信息
export function delUser(userId) {
  return request({
    url: '/standard/myUser/' + userId,
    method: 'delete'
  })
}
