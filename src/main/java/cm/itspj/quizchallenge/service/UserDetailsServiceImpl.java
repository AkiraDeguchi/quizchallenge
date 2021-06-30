package cm.itspj.quizchallenge.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cm.itspj.quizchallenge.model.CustomUser;
import cm.itspj.quizchallenge.model.CustomUserRepository;
import lombok.RequiredArgsConstructor;

/**
 * Spring Securityで使用するユーザー情報（UserDetails）を取得するためのサービスクラス
 *
 * DBに登録されているユーザー情報をUserDetailsに変換している
 *
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  /** DBのユーザー情報にアクセスするためのリポジトリ */
  private final CustomUserRepository rep;

  /** ユーザー名からユーザー情報を取得する */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // DB検索
    CustomUser user = rep.findByUsername(username);
    if (user == null) {
      // 見つからない場合は例外をスロー
      throw new UsernameNotFoundException(username + " not found.");
    }
    // UserDetailsオブジェクトを作成
    return createUserDetails(user);
  }

  private UserDetails createUserDetails(CustomUser user) {
    // ユーザー名
    String username = user.getUsername();
    // パスワード
    String password = user.getPassword();
    // 権限
    Set<GrantedAuthority> authSet = new HashSet<>();
    authSet.add(new SimpleGrantedAuthority(user.getRole()));

    return new org.springframework.security.core.userdetails.User(username, password, authSet);
  }
}
