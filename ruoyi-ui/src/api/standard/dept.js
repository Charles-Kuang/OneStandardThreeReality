import request from '@/utils/request'

// 查询单位列表
export function listDept(query) {
  return request({
    url: '/standard/dept/list',
    method: 'get',
    params: query
  })
}

// 查询单位详细
export function getDept(deptId) {
  return request({
    url: '/standard/dept/' + deptId,
    method: 'get'
  })
}

// 新增单位
export function addDept(data) {
  return request({
    url: '/standard/dept',
    method: 'post',
    data: data
  })
}

// 修改单位
export function updateDept(data) {
  return request({
    url: '/standard/dept',
    method: 'put',
    data: data
  })
}

// 删除单位
export function delDept(deptId) {
  return request({
    url: '/standard/dept/' + deptId,
    method: 'delete'
  })
}
