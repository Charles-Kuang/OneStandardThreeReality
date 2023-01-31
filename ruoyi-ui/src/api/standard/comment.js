import request from '@/utils/request'

// 查询新闻评论列表
export function listComment(query) {
  return request({
    url: '/standard/comment/list',
    method: 'get',
    params: query
  })
}

// 查询新闻评论详细
export function getComment(commentId) {
  return request({
    url: '/standard/comment/' + commentId,
    method: 'get'
  })
}

// 新增新闻评论
export function addComment(data) {
  return request({
    url: '/standard/comment',
    method: 'post',
    data: data
  })
}

// 修改新闻评论
export function updateComment(data) {
  return request({
    url: '/standard/comment',
    method: 'put',
    data: data
  })
}

// 删除新闻评论
export function delComment(commentId) {
  return request({
    url: '/standard/comment/' + commentId,
    method: 'delete'
  })
}
